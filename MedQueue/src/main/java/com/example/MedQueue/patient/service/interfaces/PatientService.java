package com.example.MedQueue.patient.service.interfaces;

import com.example.MedQueue.appointment.model.Appointment;
import com.example.MedQueue.patient.data.model.Patient;
import com.example.MedQueue.patient.dtos.request.PatientRegisterRequest;
import com.example.MedQueue.patient.dtos.responses.PatientRegisterResponses;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Component
public interface PatientService {
    PatientRegisterResponses registerPatient(PatientRegisterRequest patientRegisterRequest);

  String login(String email, String password);
  String loginWithPhoneNumber(String phoneNumber, String password);
  Appointment bookAppointment(UUID id, UUID patientId, UUID doctorId, LocalDate date, LocalTime time,
                              String description);
    Appointment cancelAppointment( UUID patientId);


}
