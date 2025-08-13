package com.example.MedQueue.auth.controller;

import com.example.MedQueue.auth.jwt.JwtUtil;
import com.example.MedQueue.user.enums.Role;
import com.example.MedQueue.user.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    record AuthRequest(String email, String password) {}
    record AuthResponse(String token) {}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password())
        );

        var principal = auth.getPrincipal();
        // get username and roles from repository or auth
        var user = userRepository.findByEmail(req.email()).orElseThrow();
        var roles = java.util.List.of(user.getRoles().add( Role.DOCTOR )); // single role
        String token = jwtUtil.generateToken(user.getEmail(), roles);
        return ResponseEntity.ok(new AuthResponse(token));
    }


}
