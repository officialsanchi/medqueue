package com.example.MedQueue.patient.service.implementation;

import com.example.MedQueue.appointment.enums.AppointmentStatus;
import com.example.MedQueue.appointment.model.Appointment;
import com.example.MedQueue.appointment.repo.AppointmentRepository;
import com.example.MedQueue.auth.enums.Role;
import com.example.MedQueue.patient.data.model.Patient;
import com.example.MedQueue.patient.data.repository.PatientRepository;
import com.example.MedQueue.patient.dtos.request.PatientRegisterRequest;
import com.example.MedQueue.patient.dtos.responses.PatientRegisterResponses;
import com.example.MedQueue.patient.service.interfaces.PatientService;
import com.example.MedQueue.user.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.UUID;


@Service
@RequiredArgsConstructor

public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PatientRegisterResponses registerPatient(PatientRegisterRequest patientRegisterRequest) {
        Patient patient = Patient.builder()
                .id(UUID.randomUUID() )
                .roles( Collections.singleton( Role.PATIENT )
                .fullName( patientRegisterRequest.getFullName() )
                .email( patientRegisterRequest.getEmail() )
                .password( passwordEncoder.encode( patientRegisterRequest.getPassword() ) )
                .phoneNumber( patientRegisterRequest.getPhoneNumber() )
                .build();
        patientRepository.save( patient );

        PatientRegisterResponses responses = new PatientRegisterResponses();
        responses.setMessage("User registered successfully");
        return responses;

    }

    @Override
    public String login(String email, String password) {
        if (patientRepository.findByEmail( email ).getPassword().equals( password )) {
            return "Login successful";
        } else {
            return "Invalid credentials";
        }


    }

    @Override
    public String loginWithPhoneNumber(String phoneNumber, String password) {
        if (patientRepository.findByPhoneNumber( phoneNumber ).getPassword().equals( password )) {
            return "Login successful";
        } else {
            return "Invalid credentials";
        }

    }

    @Override
    public Appointment bookAppointment(UUID id, UUID patientId, UUID doctorId, LocalDate date, LocalTime time, String description) {
        Appointment appointment = Appointment.builder()
                .id( id )
                .doctorId( doctorId )
                .patientId( patientId )
                .date( date )
                .time( time )
                .description( description )
                .status( AppointmentStatus.BOOKED )
                .build();
        appointmentRepository.save( appointment );
        return appointment;

    }


    @Override
    public Appointment cancelAppointment( UUID patientId) {

        Appointment appointment = (Appointment) appointmentRepository
                .findByPatientId( patientId);

        appointment.setStatus(AppointmentStatus.CANCELLED);

        return appointmentRepository.save(appointment);
    }


}
