package com.example.MedQueue.appointment.service.implementation;

import com.example.MedQueue.appointment.model.Appointment;
import com.example.MedQueue.appointment.repo.AppointmentRepository;
import com.example.MedQueue.appointment.service.interfaces.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepo;



    @Override
    public String bookAppointment(UUID id, UUID patientId, UUID doctorId, LocalDate date, LocalTime time, String description) {
        Appointment  appointment = Appointment.builder()
                .id( id )
                .description( description )
                .patientId( patientId )
                .doctorId( doctorId )
                .date( LocalDate.now() )
                .time( LocalTime.now())
                .build();
        appointmentRepo.save( appointment );
       return "Appointment booked successfully";
    }
    @Override
    public String cancelAppointment( UUID patientId) {
        Appointment appointment = appointmentRepo.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.cancel(patientId);

        appointmentRepo.save(appointment);
        return "Appointment cancelled.";
    }
    @Override
    public String rescheduleAppointment(UUID appointmentId, UUID patientId, LocalDate newDate, LocalTime newTime) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        if (!appointment.getPatientId().equals(patientId)) {
            throw new RuntimeException("Unauthorized reschedule attempt.");
        }
        if (appointmentRepo.existsByDoctorIdAndDateAndTime(appointment.getDoctorId(), newDate, newTime)) {
            throw new RuntimeException("Time slot already booked.");
        }

        appointment.setDate(newDate);
        appointment.setTime(newTime);
        appointmentRepo.save(appointment);
        return "Appointment rescheduled.";
    }



}
