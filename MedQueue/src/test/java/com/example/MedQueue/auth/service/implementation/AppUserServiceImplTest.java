//package com.example.MedQueue.auth.service.implementation;
//
//import com.example.MedQueue.auth.enums.Role;
//import com.example.MedQueue.auth.models.AppUser;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Optional;
//
//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
//import static org.hamcrest.Matchers.any;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class AppUserServiceImplTest {
//    @Mock
//    private AppUsersRepository userRepository;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private AppUserServiceImpl appUserService;
//
//    @Test
//    void registerUser_Successful() {
//        String email = "test@example.com";
//        String phone = "08123456789";
//
//        when(userRepository.findByEmail(email)).thenReturn( Optional.empty());
//        when(userRepository.existsByPhoneNumber(phone)).thenReturn(false);
//        when(passwordEncoder.encode("password")).thenReturn("encoded_password");
//
//        AppUser savedUser = new AppUser();
//        when(userRepository.save(any(AppUser.class))).thenReturn(savedUser);
//
//        AppUser result = appUserService.registerUser("Test User", email, phone, "password", Role.PATIENT);
//        assertNotNull(result);
//        verify(userRepository).save(any(AppUser.class));
//    }
//    @Test
//    void registerUser_InvalidEmail_ThrowsException() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                appUserService.registerUser("Test", "bademail", "08123456789", "password", Role.DOCTOR)
//        );
//        assertEquals("Invalid email format.", exception.getMessage());
//    }
//    @Test
//    void registerUser_InvalidPhone_ThrowsException() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                appUserService.registerUser("Test", "test@example.com", "123", "password", Role.ADMIN)
//        );
//        assertEquals("Invalid phone number format.", exception.getMessage());
//    }
//    @Test
//    void registerUser_EmailExists_ThrowsException() {
//        String email = "existing@example.com";
//        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new AppUser()));
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                appUserService.registerUser("Test", email, "08123456789", "password", Role.PATIENT)
//        );
//        assertEquals("Email already exists.", exception.getMessage());
//    }
//    @Test
//    void registerUser_PhoneExists_ThrowsException() {
//        String email = "test@example.com";
//        String phone = "08123456789";
//        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
//        when(userRepository.existsByPhoneNumber(phone)).thenReturn(true);
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                appUserService.registerUser("Test", email, phone, "password", Role.ADMIN)
//        );
//        assertEquals("Phone number already exists.", exception.getMessage());
//    }
//    @Test
//    void login_WithEmail_Successful() {
//        String email = "user@example.com";
//        AppUser user = new AppUser();
//        user.setEmail(email);
//        user.setPassword("encoded");
//
//        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
//        when(passwordEncoder.matches("password", "encoded")).thenReturn(true);
//
//        AppUser result = appUserService.login(email, "password");
//        assertEquals(email, result.getEmail());
//    }
//    @Test
//    void login_WithPhone_Successful() {
//        String phone = "08123456789";
//        AppUser user = new AppUser();
//        user.setPhoneNumber(phone);
//        user.setPassword("encoded");
//
//        when(userRepository.findByPhoneNumber(phone)).thenReturn(Optional.of(user));
//        when(passwordEncoder.matches("password", "encoded")).thenReturn(true);
//
//        AppUser result = appUserService.login(phone, "password");
//        assertEquals(phone, result.getPhoneNumber());
//    }
//    @Test
//    void login_InvalidIdentifierFormat_ThrowsException() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                appUserService.login("invalid_id", "password")
//        );
//        assertEquals("Invalid email or phone number format.", exception.getMessage());
//    }
//    @Test
//    void login_UserNotFound_ThrowsException() {
//        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                appUserService.login("test@example.com", "password")
//        );
//        assertEquals("User not found with provided email or phone number.", exception.getMessage());
//    }
//    @Test
//    void login_WrongPassword_ThrowsException() {
//        AppUser user = new AppUser();
//        user.setPassword("encoded");
//
//        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
//        when(passwordEncoder.matches("wrong", "encoded")).thenReturn(false);
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                appUserService.login("test@example.com", "wrong")
//        );
//        assertEquals("Invalid password.", exception.getMessage());
//    }
//
//
//
//
//}