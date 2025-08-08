package com.example.MedQueue.patient.data.repository;

import com.example.MedQueue.appointment.model.Appointment;
import com.example.MedQueue.patient.data.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmail(String email);
    Patient findByPhoneNumber(String phoneNumber);


}
