package com.example.MedQueue.auth.models;

import com.example.MedQueue.auth.enums.Role;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;
import java.util.Set;
import java.util.UUID;




public interface AppUser {

    UUID getId();
    void setId(UUID id);

    String getFullName();
    void setFullName(String fullName);

    String getEmail();
    void setEmail(String email);

    String getPassword();
    void setPassword(String password);

    String getPhoneNumber();
    void setPhoneNumber(String phoneNumber);

    Set<Role> getRoles();
    void setRoles(Set<Role> roles);
}
