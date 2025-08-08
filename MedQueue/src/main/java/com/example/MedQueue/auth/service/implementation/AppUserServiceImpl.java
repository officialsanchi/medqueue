//package com.example.MedQueue.auth.service.implementation;
//
//import com.example.MedQueue.auth.dtos.request.LoginRequest;
//import com.example.MedQueue.auth.enums.Role;
//import com.example.MedQueue.auth.models.AppUser;
//import com.example.MedQueue.auth.repo.AppUsersRepository;
//import com.example.MedQueue.auth.service.interfaces.AppUserService;
//import com.example.MedQueue.auth.util.Validations;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//import java.util.Set;
//
//@Service
//
//
//public class AppUserServiceImpl  implements AppUserService {
//
//    public AppUserServiceImpl(AppUsersRepository userRepository,
//                              PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    private final AppUsersRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//
//    @Override
//    public AppUser registerUser(String fullName, String email, String phoneNumber, String password, Role role) {
//        validateEmailAndPhone(email, phoneNumber);
//        String emailCheck = emailExists(email);
//        if (emailCheck != null) {
//            throw new IllegalArgumentException(emailCheck);
//        }
//        String phoneCheck = phoneNumberExists(phoneNumber);
//        if (phoneCheck != null) {
//            throw new IllegalArgumentException(phoneCheck);
//        }
//        AppUser user = new AppUser();
//        user.setFullName( fullName );
//        user.setEmail( email );
//        user.setPhoneNumber( phoneNumber );
//        user.setPassword( passwordEncoder.encode( password ) );
//        user.setRoles( Set.of( role ) );
//        return userRepository.save( user );
//    }
//    private void validateEmailAndPhone(String email, String phoneNumber) {
//        if (!Validations.isValidEmail( email )) {
//            throw new IllegalArgumentException("Invalid email format.");
//        }
//        if (!Validations.isValidPhone(phoneNumber)) {
//            throw new IllegalArgumentException("Invalid phone number format.");
//        }
//        System.out.println("Email and phone number are valid.");
//    }
//    private String emailExists(String email) {
//        Optional<AppUser> emailTaken = userRepository.findByEmail(email);
//        if (emailTaken.isPresent()) {
//            return "Email already exists.";
//        }
//        return null;
//    }
//    private String phoneNumberExists(String phoneNumber) {
//        boolean phoneNumberTaken = userRepository.existsByPhoneNumber(phoneNumber);
//        if (phoneNumberTaken) {
//            return "Phone number already exists.";
//        }
//        return null;
//    }
//
//
//@Override
//    public AppUser login(String username, String password) {
//        Optional<AppUser> userOptional;
//        if (Validations.isValidEmail(username)) {
//            userOptional = userRepository.findByEmail(username);
//        } else if (Validations.isValidPhone(username)) {
//            userOptional = userRepository.findByPhoneNumber(username);
//        } else {
//            throw new IllegalArgumentException("Invalid email or phone number format.");
//        }
//
//        AppUser user = userOptional.orElseThrow(() ->
//                new IllegalArgumentException("User not found with provided email or phone number.")
//        );
//
//
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new IllegalArgumentException("Invalid password.");
//        }
//        return user;
//    }
//
//}
//
