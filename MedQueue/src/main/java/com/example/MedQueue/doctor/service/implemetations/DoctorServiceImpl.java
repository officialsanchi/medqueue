package com.example.MedQueue.doctor.service.implemetations;

import com.example.MedQueue.auth.enums.Role;
import com.example.MedQueue.doctor.data.model.Doctor;
import com.example.MedQueue.doctor.data.repository.DoctorRepository;
import com.example.MedQueue.doctor.enums.Specialist;
import com.example.MedQueue.doctor.service.interfaces.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Override
    public String registerDoctor(  UUID id ,String description, String fullName, String email, String password, int yearsOfExperience, String phoneNumber) {
        Doctor doctor = Doctor.builder()
                .id( id)
                .roles( Collections.singleton( Role.DOCTOR ) )
                .password(password)
                .phoneNumber(phoneNumber)
                .yearsOfExperience(yearsOfExperience)
                .email(email)
                .fullName(fullName)
                .specialists( Collections.singleton( Specialist.NEUROLOGIST ) )
                .description( description )
                .build();
        doctorRepository.save( doctor );

        return "Doctor registered successfully!";
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
