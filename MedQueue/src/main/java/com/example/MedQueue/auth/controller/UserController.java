package com.example.MedQueue.auth.controller;

import com.example.MedQueue.auth.dtos.request.RegisterRequest;
import com.example.MedQueue.auth.enums.Role;
import com.example.MedQueue.auth.models.AppUser;
import com.example.MedQueue.doctor.service.implemetations.DoctorServiceImpl;
import com.example.MedQueue.patient.service.implementation.PatientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {
//    private final DoctorServiceImpl doctorService;
//    private final PatientServiceImpl patientService;
//
//    @PostMapping("/register-doctor")
//    @PreAuthorize("hasRole('ADMIN')")
//    public AppUser registerDoctor(@RequestBody RegisterRequest request) {
//        return doctorService.registerDoctor(
//                request.getFullName(),
//                request.getEmail(),
//                request.getPhoneNumber(),
//                request.getPassword(),
//                Role.DOCTOR
//        );
//    }
//
//    @PostMapping("/register-patient")
//    @PreAuthorize("hasRole('ADMIN')")
//    public AppUser registerPatient(@RequestBody RegisterRequest request) {
//        return patientService.;
//    }
}
