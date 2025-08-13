package com.example.MedQueue.doctor.controllers;

import com.example.MedQueue.doctor.dtos.request.RegisterDoctorRequest;
import com.example.MedQueue.doctor.dtos.responses.RegisterDoctorResponses;
import com.example.MedQueue.doctor.service.implemetations.DoctorServiceImpl;
import com.example.MedQueue.user.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;
    @PostMapping("/appointments")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<?> createAppointment(@RequestBody RegisterDoctorRequest registerDoctorRequest) {
        return ResponseEntity.ok("appointment created");
    }

}
