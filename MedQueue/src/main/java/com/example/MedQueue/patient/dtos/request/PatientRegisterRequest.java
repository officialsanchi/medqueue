package com.example.MedQueue.patient.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class PatientRegisterRequest {
   private  String fullName;
    private String email;
    private String password;
    private String phoneNumber;
}
