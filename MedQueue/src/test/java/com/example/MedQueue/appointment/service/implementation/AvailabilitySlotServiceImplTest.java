package com.example.MedQueue.appointment.service.implementation;

import com.example.MedQueue.appointment.model.AvailabilitySlot;
import com.example.MedQueue.appointment.repo.AvailabilitySlotRepository;
import com.example.MedQueue.appointment.service.implementation.AvailabilitySlotServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.ExpectedCount.times;


class AvailabilitySlotServiceImplTest {

    @Mock
    private AvailabilitySlotRepository availabilityRepo;

    @InjectMocks
    private AvailabilitySlotServiceImpl availabilityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDefineAvailability_SavesSlotAndReturnsConfirmationMessage() {
        String doctorId = "doc123";
        LocalDate date = LocalDate.now();
        List<LocalTime> times = List.of(LocalTime.of(10, 0), LocalTime.of(11, 0));

        String result = availabilityService.defineAvailability(doctorId, date, times);

        verify(availabilityRepo, times(1)).save(any(AvailabilitySlot.class));
        assertTrue(result.contains("Availability set for doctor on " + date));
    }

    @Test
    void testGetAvailability_ReturnsAvailabilitySlot() {
        String doctorId = "doc123";
        LocalDate date = LocalDate.of(2025, 8, 1);
        AvailabilitySlot slot = new AvailabilitySlot(null, doctorId, date, List.of(LocalTime.of(9, 0)));

        when(availabilityRepo.findByDoctorIdAndDate(doctorId, date)).thenReturn(Optional.of(slot));

        AvailabilitySlot result = availabilityService.getAvailability(doctorId, date);

        assertNotNull(result);
        assertEquals(slot, result);
    }

    @Test
    void testGetAvailability_ThrowsException_WhenSlotNotFound() {
        String doctorId = "doc404";
        LocalDate date = LocalDate.now();

        when(availabilityRepo.findByDoctorIdAndDate(doctorId, date)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                availabilityService.getAvailability(doctorId, date));

        assertEquals("No availability found", exception.getMessage());
    }

    @Test
    void testDefineAvailability_CreatesCorrectSlotData() {
        String doctorId = "doc321";
        LocalDate date = LocalDate.of(2025, 9, 1);
        List<LocalTime> slots = List.of(LocalTime.of(8, 0), LocalTime.of(9, 0));

        availabilityService.defineAvailability(doctorId, date, slots);

        verify(availabilityRepo).save(argThat(slot ->
                slot.getDoctorId().equals(doctorId)
                        && slot.getDate().equals(date)
                        && slot.getAvailableTimes().equals(slots)
        ));
    }

    @Test
    void testDefineAvailability_MultipleSlotsHandled() {
        String doctorId = "docMulti";
        LocalDate date = LocalDate.now();
        List<LocalTime> slots = List.of(LocalTime.of(8, 0), LocalTime.of(9, 0), LocalTime.of(10, 0));

        availabilityService.defineAvailability(doctorId, date, slots);

        verify(availabilityRepo, times(1)).save(any(AvailabilitySlot.class));
    }
}