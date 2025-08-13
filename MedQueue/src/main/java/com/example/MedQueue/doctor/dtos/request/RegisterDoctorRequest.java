package com.example.MedQueue.doctor.dtos.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
@Builder

public class RegisterDoctorRequest {

    private String description;
    private String image;
    private String school;
    private String certification;
    private String fullName;
    private String email;
    private String password;
    private int yearsOfExperience;
    private String phoneNumber;
}
