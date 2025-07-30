package com.example.MedQueue.appointment.service.interfaces;

import com.example.MedQueue.appointment.model.Appointment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public interface AppointmentService {
    String bookAppointment(String patientId, String doctorId, LocalDate date, LocalTime time);
    List<Appointment> viewPatientAppointments(String patientId);
    String cancelAppointment(String appointmentId, String patientId);
    String rescheduleAppointment(String appointmentId, String patientId, LocalDate newDate, LocalTime newTime);
    List<Appointment> viewDoctorAppointments(String doctorId, LocalDate date);
    List<Appointment> getAppointmentsForDoctor(String doctorId);
    List<Appointment> getAppointmentsForPatient(String patientId);
    void cancelAppointment(String appointmentId);
}
