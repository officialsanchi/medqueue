package com.example.MedQueue.appointment.repo;

import com.example.MedQueue.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository  extends JpaRepository<Appointment, UUID> {
    boolean existsByDoctorIdAndDateAndTime(String doctorId, LocalDate date, LocalTime time);
    boolean existsByPatientIdAndDate(String patientId, LocalDate date);
    List<Appointment> findByPatientId(String patientId);
    List<Appointment> findByDoctorIdAndDate(String doctorId, LocalDate date);
    List<Appointment>findByDoctorId( String doctorId);
    List<Appointment> findByDoctorIdAndDate(UUID doctorId, LocalDate date);

    List<Appointment> findByPatientIdOrderByDateDesc(UUID patientId);
}
