package com.example.MedQueue.doctor.service.interfaces;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface DoctorService {
 String registerDoctor(UUID id, String description, String fullName, String email, String password, int yearsOfExperience, String phoneNumber);
 String loginDoctor(String email, String password);
 String loginDoctorWithPhoneNumber(String phoneNumber, String password);
}
