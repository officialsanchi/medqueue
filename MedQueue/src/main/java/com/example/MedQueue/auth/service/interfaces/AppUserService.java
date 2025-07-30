package com.example.MedQueue.auth.service.interfaces;


import com.example.MedQueue.auth.dtos.request.RegisterRequest;
import com.example.MedQueue.auth.enums.Role;
import com.example.MedQueue.auth.models.AppUser;
import org.springframework.stereotype.Component;

@Component
public interface AppUserService {
    AppUser registerUser(String fullName, String email, String phoneNumber, String password, Role role);
    AppUser login(String username, String password);
}
