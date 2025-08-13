package com.example.MedQueue.doctor.service.interfaces;

import com.example.MedQueue.doctor.dtos.request.RegisterDoctorRequest;
import com.example.MedQueue.doctor.dtos.responses.RegisterDoctorResponses;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface DoctorService {
 RegisterDoctorResponses registerDoctor(RegisterDoctorRequest registerDoctorRequest);
 String loginDoctor(String email, String password);
 String loginDoctorWithPhoneNumber(String phoneNumber, String password);
}
