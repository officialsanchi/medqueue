package com.example.MedQueue.patient.controllers;

import com.example.MedQueue.patient.dtos.request.PatientRegisterRequest;
import com.example.MedQueue.patient.dtos.responses.PatientRegisterResponses;
import com.example.MedQueue.patient.service.implementation.PatientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/patient")

public class PatientController {
    @PostMapping("/book")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> bookAppointment(@RequestBody Object body) {
        // book appointment
        return ResponseEntity.ok("booked");
    }
}
