package com.example.MedQueue.patient.service.implementation;

import com.example.MedQueue.auth.enums.Role;
import com.example.MedQueue.patient.data.model.Patient;
import com.example.MedQueue.patient.data.repository.PatientRepository;
import com.example.MedQueue.patient.service.interfaces.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;


@Service
@RequiredArgsConstructor

public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    @Override
    public String register(UUID id, String fullName, String email, String password, String phoneNumber) {
        Patient patient = Patient.builder()
                .id( id )
                .roles( Collections.singleton( Role.PATIENT ) )
                .fullName(fullName)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();
        patientRepository.save(patient);
        return "User registered successfully";
    }

    @Override
    public String login(String email, String password) {
        if (patientRepository.findByEmail(email).getPassword().equals(password)) {
            return "Login successful";
        } else {
            return "Invalid credentials";
        }


    }

    @Override
    public String loginWithPhoneNumber(String phoneNumber, String password) {
        if (patientRepository.findByPhoneNumber(phoneNumber).getPassword().equals(password)) {
            return "Login successful";
        } else {
            return "Invalid credentials";
        }

    }



}
