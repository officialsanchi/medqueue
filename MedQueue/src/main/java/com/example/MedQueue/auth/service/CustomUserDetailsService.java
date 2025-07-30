package com.example.MedQueue.auth.service;

import com.example.MedQueue.auth.enums.Role;
import com.example.MedQueue.auth.models.AppUser;
import com.example.MedQueue.auth.repo.AppUsersRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service


public class CustomUserDetailsService implements UserDetailsService {
    public CustomUserDetailsService(AppUsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final AppUsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return User.builder()
                .username(appUser.getEmail())
                .password(appUser.getPassword())
                .roles( String.valueOf( appUser.getRoles().add( Role.valueOf( "ROLE_" + appUser.getRoles() ) ) ) )
                .build();
    }
}
