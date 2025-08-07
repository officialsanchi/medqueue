package com.example.MedQueue.doctor.data.repository;

import com.example.MedQueue.doctor.data.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByEmail(String email);
    Doctor findByPhoneNumber(String phoneNumber);
}
