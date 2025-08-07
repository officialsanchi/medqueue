package com.example.MedQueue.patient.service.interfaces;

import com.example.MedQueue.patient.data.model.Patient;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface PatientService {
    String register(UUID id, String fullName, String email, String password, String phoneNumber);

  String login(String email, String password);
  String loginWithPhoneNumber(String phoneNumber, String password);
}
