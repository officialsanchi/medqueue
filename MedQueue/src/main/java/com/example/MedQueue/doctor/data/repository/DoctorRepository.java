package com.example.MedQueue.doctor.data.repository;

import com.example.MedQueue.doctor.data.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Doctor findByEmail(String email);
    Doctor findByPhoneNumber(String phoneNumber);
}
