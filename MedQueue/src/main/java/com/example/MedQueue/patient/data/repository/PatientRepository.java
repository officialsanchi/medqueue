package com.example.MedQueue.patient.data.repository;

import com.example.MedQueue.patient.data.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmail(String email);
    Patient findByPhoneNumber(String phoneNumber);
}
