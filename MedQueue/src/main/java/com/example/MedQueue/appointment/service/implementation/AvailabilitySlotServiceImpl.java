package com.example.MedQueue.appointment.service.implementation;
import com.example.MedQueue.appointment.model.AvailabilitySlot;
import com.example.MedQueue.appointment.repo.AvailabilitySlotRepository;
import com.example.MedQueue.appointment.service.interfaces.AvailabilitySlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class AvailabilitySlotServiceImpl implements AvailabilitySlotService {
    private final AvailabilitySlotRepository availabilityRepo;

    public String defineAvailability(String doctorId, LocalDate date, List<LocalTime> timeSlots) {
        AvailabilitySlot slot = new AvailabilitySlot(null, doctorId, date, timeSlots);
        availabilityRepo.save(slot);
        return "Availability set for doctor on " + date;
    }

    public AvailabilitySlot getAvailability(String doctorId, LocalDate date) {
        return availabilityRepo.findByDoctorIdAndDate(doctorId, date)
                .orElseThrow(() -> new RuntimeException("No availability found"));
    }
}
