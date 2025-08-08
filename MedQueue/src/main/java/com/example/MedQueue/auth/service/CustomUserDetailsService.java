package com.example.MedQueue.auth.service;

import com.example.MedQueue.auth.enums.Role;
import com.example.MedQueue.auth.models.AppUser;
import com.example.MedQueue.doctor.data.model.Doctor;
import com.example.MedQueue.doctor.data.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final DoctorRepository doctorRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Doctor appUser = doctorRepository.findByEmail(email);
        if (appUser == null){
            throw new UsernameNotFoundException("User Not Found");
        }


        return User.builder()
                .username(appUser.getEmail())
                .password(appUser.getPassword())
                .roles( String.valueOf( appUser.getRoles().add( Role.valueOf( "ROLE_" + appUser.getRoles() ) ) ) )
                .build();
    }
}
