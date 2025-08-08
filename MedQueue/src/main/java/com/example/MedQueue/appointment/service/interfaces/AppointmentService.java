package com.example.MedQueue.appointment.service.interfaces;

import com.example.MedQueue.appointment.model.Appointment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Component
public interface AppointmentService {
    String bookAppointment(UUID id, UUID patientId, UUID doctorId, LocalDate date, LocalTime time, String description);
    String cancelAppointment( UUID patientId);
    String rescheduleAppointment(UUID appointmentId, UUID patientId, LocalDate newDate, LocalTime newTime);


}
