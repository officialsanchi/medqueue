package com.example.MedQueue.appointment.repo;

import com.example.MedQueue.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentRepository  extends JpaRepository<Appointment, UUID > {
    boolean existsByDoctorIdAndDateAndTime(UUID doctorId, LocalDate date, LocalTime time);
    boolean existsByPatientIdAndDate(UUID patientId, LocalDate date);
    List<Appointment> findByPatientId(UUID patientId);
    List<Appointment> findByDoctorIdAndDate(UUID doctorId, LocalDate date);
    List<Appointment>findByDoctorId( UUID doctorId);

}
