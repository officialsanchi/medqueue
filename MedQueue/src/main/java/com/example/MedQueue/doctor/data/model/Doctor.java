package com.example.MedQueue.doctor.data.model;

import com.example.MedQueue.auth.enums.Role;
import com.example.MedQueue.auth.models.AppUser;
import com.example.MedQueue.doctor.enums.Specialist;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity(name="doctors")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctors")


public class Doctor implements AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int yearsOfExperience;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<Role> roles;

    @ElementCollection(targetClass = Specialist.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "doctors_specialists", joinColumns = @JoinColumn(name = "doctors_id"))
    @Column(name = "specialist")
    private Set <Specialist> specialists;

    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String school;
    @Column(nullable = false)
    private String certification;

    public Doctor(UUID id, String fullName, String email, String password, int yearsOfExperience,
                  String phoneNumber, Set<Role> roles) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.yearsOfExperience = yearsOfExperience;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }


    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
