package com.example.MedQueue.user.controllers;


import com.example.MedQueue.doctor.data.model.Doctor;
import com.example.MedQueue.doctor.dtos.request.RegisterDoctorRequest;
import com.example.MedQueue.patient.data.model.Patient;
import com.example.MedQueue.user.enums.Role;
import com.example.MedQueue.user.repository.AppUserRepository;
import com.example.MedQueue.user.service.implementation.AdminPanelServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    record RegisterRequest(String fullName, String email, String password) {}

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register/doctor")
    public ResponseEntity<?> registerDoctor(@RequestBody RegisterDoctorRequest req) {
        Doctor d = Doctor.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .yearsOfExperience(req.getYearsOfExperience())
                .school(req.getSchool())
                .description(req.getDescription())
                .image(req.getImage())
                .phoneNumber(req.getPhoneNumber())
                .certification(req.getCertification())
                .roles( Set.of( Role.DOCTOR))
                .build();

        var saved = userRepository.save(d);
        return ResponseEntity.status( HttpStatus.CREATED).body(saved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register/patient")
    public ResponseEntity<?> registerPatient(@RequestBody RegisterRequest req) {
        Patient p = Patient.builder()
                .build();
        var saved = userRepository.save(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


}
