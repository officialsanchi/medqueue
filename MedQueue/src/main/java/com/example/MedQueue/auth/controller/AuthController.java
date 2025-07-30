package com.example.MedQueue.auth.controller;

import com.example.MedQueue.auth.dtos.request.LoginRequest;
import com.example.MedQueue.auth.dtos.request.RegisterRequest;
import com.example.MedQueue.auth.enums.Role;
import com.example.MedQueue.auth.jwt.JwtService;
import com.example.MedQueue.auth.models.AppUser;
import com.example.MedQueue.auth.service.implementation.AppUserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")

public class AuthController {
    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          AppUserServiceImpl userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AppUserServiceImpl userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return jwtService.generateToken(request.getEmail(), authentication.getAuthorities().iterator().next().getAuthority());
    }

    @PostMapping("/register-admin")
    public AppUser registerAdmin(@RequestBody RegisterRequest request) {
        return userService.registerUser(
                request.getFullName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getPassword(),
                Role.ADMIN
        );
    }
}
