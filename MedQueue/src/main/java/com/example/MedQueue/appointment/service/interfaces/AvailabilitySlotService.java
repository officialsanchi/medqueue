package com.example.MedQueue.appointment.service.interfaces;

import com.example.MedQueue.appointment.model.AvailabilitySlot;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public interface AvailabilitySlotService {
    String defineAvailability(String doctorId, LocalDate date, List<LocalTime> timeSlots);
    AvailabilitySlot getAvailability(String doctorId, LocalDate date);
}
