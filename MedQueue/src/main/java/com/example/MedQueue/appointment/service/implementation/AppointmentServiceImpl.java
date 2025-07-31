package com.example.MedQueue.appointment.service.implementation;

import com.example.MedQueue.appointment.enums.AppointmentStatus;
import com.example.MedQueue.appointment.model.Appointment;
import com.example.MedQueue.appointment.model.AvailabilitySlot;
import com.example.MedQueue.appointment.repo.AppointmentRepository;
import com.example.MedQueue.appointment.repo.AvailabilitySlotRepository;
import com.example.MedQueue.appointment.service.interfaces.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepo;
    private final AvailabilitySlotRepository availabilityRepo;

    @Override
    public String bookAppointment(String patientId, String doctorId, LocalDate date, LocalTime time) {
        validateDoctorAvailability(doctorId, date, time);
        validatePatientAppointment(patientId, date);
        validateDoctorSchedule(doctorId, date, time);

        Appointment appointment = new Appointment(null, patientId, doctorId, date, time, AppointmentStatus.BOOKED);
        appointmentRepo.save(appointment);
        return "Appointment booked successfully.";
    }
    private void validateDoctorAvailability(String doctorId, LocalDate date, LocalTime time) {
        if (appointmentRepo.existsByDoctorIdAndDateAndTime(doctorId, date, time)) {
            throw new RuntimeException("Doctor already been booked.");
        }
    }

    private void validatePatientAppointment(String patientId, LocalDate date) {
        if (appointmentRepo.existsByPatientIdAndDate(patientId, date)) {
            throw new RuntimeException("Patient already has an appointment on this date.");
        }
    }

    private void validateDoctorSchedule(String doctorId, LocalDate date, LocalTime time) {
        AvailabilitySlot slot = availabilityRepo.findByDoctorIdAndDate(doctorId, date)
                .orElseThrow(() -> new RuntimeException("Doctor has not set availability for that date."));

        if (!slot.getAvailableTimes().contains(time)) {
            throw new RuntimeException("Time not available.");
        }
    }

    @Override
    public List<Appointment> viewPatientAppointments(String patientId) {
        return appointmentRepo.findByPatientId(patientId);
    }
    @Override
    public String cancelAppointment(String appointmentId, String patientId) {
        Appointment appt = appointmentRepo.findById( UUID.fromString( appointmentId ) )
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        if (!appt.getPatientId().equals(patientId)) {
            throw new RuntimeException("Unauthorized cancel attempt.");
        }
        appt.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepo.save(appt);
        return "Appointment cancelled.";
    }
    @Override
    public String rescheduleAppointment(String appointmentId, String patientId, LocalDate newDate, LocalTime newTime) {
        Appointment appt = appointmentRepo.findById( UUID.fromString( appointmentId ) )
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        if (!appt.getPatientId().equals(patientId)) {
            throw new RuntimeException("Unauthorized reschedule attempt.");
        }
        if (appointmentRepo.existsByDoctorIdAndDateAndTime(appt.getDoctorId(), newDate, newTime)) {
            throw new RuntimeException("Time slot already booked.");
        }

        appt.setDate(newDate);
        appt.setTime(newTime);
        appointmentRepo.save(appt);
        return "Appointment rescheduled.";
    }

        @Override
    public List<Appointment> viewDoctorAppointments(String doctorId, LocalDate date) {
        return appointmentRepo.findByDoctorIdAndDate(doctorId, date);
    }
    @Override
    public List<Appointment> getAppointmentsForPatient(String patientId) {
        return appointmentRepo.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(String doctorId) {
        return appointmentRepo.findByDoctorId(doctorId);


    }
    @Override

    public void cancelAppointment(String appointmentId) {
        appointmentRepo.deleteById( UUID.fromString( appointmentId ) );
    }
}
