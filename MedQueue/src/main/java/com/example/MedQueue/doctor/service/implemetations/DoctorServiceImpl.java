package com.example.MedQueue.doctor.service.implemetations;


import com.example.MedQueue.doctor.data.model.Doctor;
import com.example.MedQueue.doctor.data.repository.DoctorRepository;
import com.example.MedQueue.doctor.dtos.request.RegisterDoctorRequest;
import com.example.MedQueue.doctor.dtos.responses.RegisterDoctorResponses;
import com.example.MedQueue.doctor.enums.Specialist;
import com.example.MedQueue.doctor.service.interfaces.DoctorService;
import com.example.MedQueue.user.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Collections;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterDoctorResponses registerDoctor(RegisterDoctorRequest registerDoctorRequest) {
        Doctor doctor = Doctor.builder()
                .id(UUID.randomUUID())
                .roles( Collections.singleton( Role.DOCTOR ) )
                .image( registerDoctorRequest.getImage() )
                .certification( registerDoctorRequest.getCertification() )
                .school(registerDoctorRequest.getSchool())
                .password(passwordEncoder.encode( registerDoctorRequest.getPassword()))
                .phoneNumber(registerDoctorRequest.getPhoneNumber())
                .yearsOfExperience(registerDoctorRequest.getYearsOfExperience())
                .email(registerDoctorRequest.getEmail())
                .fullName(registerDoctorRequest.getFullName())
                .specialists( Collections.singleton( Specialist.NEUROLOGIST ) )
                .description( registerDoctorRequest.getDescription()  )
                .build();
        doctorRepository.save( doctor );
        RegisterDoctorResponses registerDoctorResponses = new RegisterDoctorResponses();
        registerDoctorResponses.setMessage("Doctor registered successfully");
        return registerDoctorResponses;

    }

    @Override
    public String loginDoctor(String email, String password) {
        if (doctorRepository.findByEmail(email).getPassword().equals(password)) {
            return "Login successful";
        } else if (!doctorRepository.findByEmail(email).getPassword().equals(password)){
            return "Wrong Password";
        }
        return "";
    }

    @Override
    public String loginDoctorWithPhoneNumber(String phoneNumber, String password) {
        if(doctorRepository.findByPhoneNumber(phoneNumber).getPassword().equals(password)){
            return "Login Successful";
        } else if (!doctorRepository.findByPhoneNumber(phoneNumber).getPassword().equals(password)) {
            return "Wrong Password";

        }
        return "";
    }
}
