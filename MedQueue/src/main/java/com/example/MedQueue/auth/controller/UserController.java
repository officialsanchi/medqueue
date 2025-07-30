package com.example.MedQueue.auth.controller;

import com.example.MedQueue.auth.dtos.request.RegisterRequest;
import com.example.MedQueue.auth.enums.Role;
import com.example.MedQueue.auth.models.AppUser;
import com.example.MedQueue.auth.service.implementation.AppUserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")

public class UserController {
    public UserController(AppUserServiceImpl userService) {
        this.userService = userService;
    }

    private final AppUserServiceImpl userService;

    @PostMapping("/register-doctor")
    @PreAuthorize("hasRole('ADMIN')")
    public AppUser registerDoctor(@RequestBody RegisterRequest request) {
        return userService.registerUser(
                request.getFullName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getPassword(),
                Role.DOCTOR
        );
    }

    @PostMapping("/register-patient")
    @PreAuthorize("hasRole('ADMIN')")
    public AppUser registerPatient(@RequestBody RegisterRequest request) {
        return userService.registerUser(
                request.getFullName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getPassword(),
                Role.PATIENT
        );
    }
}
