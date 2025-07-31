package com.example.MedQueue.appointment.service.implementation;

import com.example.MedQueue.appointment.enums.AppointmentStatus;
import com.example.MedQueue.appointment.model.Appointment;
import com.example.MedQueue.appointment.model.AvailabilitySlot;
import com.example.MedQueue.appointment.repo.AppointmentRepository;
import com.example.MedQueue.appointment.repo.AvailabilitySlotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AppointmentServiceImplTest {

    @InjectMocks
    private AppointmentServiceImpl service;

    @Mock
    private AppointmentRepository appointmentRepo;

    @Mock
    private AvailabilitySlotRepository availabilityRepo;

    @Test
    void testBookAppointment_Success() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(10, 0);

        when(appointmentRepo.existsByDoctorIdAndDateAndTime("doc1", date, time)).thenReturn(false);
        when(appointmentRepo.existsByPatientIdAndDate("pat1", date)).thenReturn(false);
        when(availabilityRepo.findByDoctorIdAndDate("doc1", date))
                .thenReturn(Optional.of(new AvailabilitySlot("doc1", date, List.of(time))));

        String result = service.bookAppointment("pat1", "doc1", date, time);
        assertEquals("Appointment booked successfully.", result);
        verify(appointmentRepo).save(any(Appointment.class));
    }

    @Test
    void testBookAppointment_DoctorAlreadyBooked() {
        when(appointmentRepo.existsByDoctorIdAndDateAndTime(anyString(), any(), any())).thenReturn(true);

        assertThrows(RuntimeException.class, () ->
                service.bookAppointment("pat1", "doc1", LocalDate.now(), LocalTime.of(10, 0)));
    }

    @Test
    void testBookAppointment_PatientAlreadyHasAppointment() {
        when(appointmentRepo.existsByDoctorIdAndDateAndTime(any(), any(), any())).thenReturn(false);
        when(appointmentRepo.existsByPatientIdAndDate(any(), any())).thenReturn(true);

        assertThrows(RuntimeException.class, () ->
                service.bookAppointment("pat1", "doc1", LocalDate.now(), LocalTime.of(10, 0)));
    }

    @Test
    void testBookAppointment_DoctorHasNotSetAvailability() {
        when(appointmentRepo.existsByDoctorIdAndDateAndTime(any(), any(), any())).thenReturn(false);
        when(appointmentRepo.existsByPatientIdAndDate(any(), any())).thenReturn(false);
        when(availabilityRepo.findByDoctorIdAndDate(any(), any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                service.bookAppointment("pat1", "doc1", LocalDate.now(), LocalTime.of(10, 0)));
    }

    @Test
    void testBookAppointment_TimeNotAvailable() {
        LocalTime requestedTime = LocalTime.of(10, 0);
        List<LocalTime> availableTimes = List.of(LocalTime.of(11, 0));

        when(appointmentRepo.existsByDoctorIdAndDateAndTime(any(), any(), any())).thenReturn(false);
        when(appointmentRepo.existsByPatientIdAndDate(any(), any())).thenReturn(false);
        when(availabilityRepo.findByDoctorIdAndDate(any(), any()))
                .thenReturn(Optional.of(new AvailabilitySlot("doc1", LocalDate.now(), availableTimes)));

        assertThrows(RuntimeException.class, () ->
                service.bookAppointment("pat1", "doc1", LocalDate.now(), requestedTime));
    }

    @Test
    void testCancelAppointmentByPatient_Success() {
        Appointment appt = new Appointment();
        appt.setId(UUID.randomUUID());
        appt.setPatientId("pat1");

        when(appointmentRepo.findById(any())).thenReturn(Optional.of(appt));

        String result = service.cancelAppointment(appt.getId().toString(), "pat1");
        assertEquals("Appointment cancelled.", result);
        assertEquals(AppointmentStatus.CANCELLED, appt.getStatus());
        verify(appointmentRepo).save(appt);
    }

    @Test
    void testCancelAppointment_Unauthorized() {
        Appointment appt = new Appointment();
        appt.setPatientId("someone_else");

        when(appointmentRepo.findById(any())).thenReturn(Optional.of(appt));

        assertThrows(RuntimeException.class, () ->
                service.cancelAppointment(UUID.randomUUID().toString(), "pat1"));
    }

    @Test
    void testCancelAppointment_NotFound() {
        when(appointmentRepo.findById(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                service.cancelAppointment(UUID.randomUUID().toString(), "pat1"));
    }

    @Test
    void testRescheduleAppointment_Success() {
        Appointment appt = new Appointment();
        appt.setPatientId("pat1");
        appt.setDoctorId("doc1");

        when(appointmentRepo.findById(any())).thenReturn(Optional.of(appt));
        when(appointmentRepo.existsByDoctorIdAndDateAndTime(any(), any(), any())).thenReturn(false);

        String result = service.rescheduleAppointment(UUID.randomUUID().toString(), "pat1", LocalDate.now(), LocalTime.NOON);
        assertEquals("Appointment rescheduled.", result);
        verify(appointmentRepo).save(appt);
    }

    @Test
    void testRescheduleAppointment_Unauthorized() {
        Appointment appt = new Appointment();
        appt.setPatientId("someone_else");

        when(appointmentRepo.findById(any())).thenReturn(Optional.of(appt));

        assertThrows(RuntimeException.class, () ->
                service.rescheduleAppointment(UUID.randomUUID().toString(), "pat1", LocalDate.now(), LocalTime.NOON));
    }

    @Test
    void testRescheduleAppointment_TimeSlotAlreadyBooked() {
        Appointment appt = new Appointment();
        appt.setPatientId("pat1");
        appt.setDoctorId("doc1");

        when(appointmentRepo.findById(any())).thenReturn(Optional.of(appt));
        when(appointmentRepo.existsByDoctorIdAndDateAndTime(any(), any(), any())).thenReturn(true);

        assertThrows(RuntimeException.class, () ->
                service.rescheduleAppointment(UUID.randomUUID().toString(), "pat1", LocalDate.now(), LocalTime.NOON));
    }

    @Test
    void testRescheduleAppointment_NotFound() {
        when(appointmentRepo.findById(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                service.rescheduleAppointment(UUID.randomUUID().toString(), "pat1", LocalDate.now(), LocalTime.NOON));
    }

    @Test
    void testViewPatientAppointments() {
        when(appointmentRepo.findByPatientId("pat1")).thenReturn(List.of(new Appointment()));

        List<Appointment> appointments = service.viewPatientAppointments("pat1");
        assertEquals(1, appointments.size());
    }

    @Test
    void testViewDoctorAppointments() {
        when(appointmentRepo.findByDoctorIdAndDate("doc1", LocalDate.now()))
                .thenReturn(List.of(new Appointment()));

        List<Appointment> appointments = service.viewDoctorAppointments("doc1", LocalDate.now());
        assertEquals(1, appointments.size());
    }

    @Test
    void testCancelAppointmentByIdOnly() {
        UUID id = UUID.randomUUID();
        service.cancelAppointment(id.toString());
        verify(appointmentRepo).deleteById(id);
    }
}
