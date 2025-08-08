package com.example.MedQueue.user.service.implementation;

import com.example.MedQueue.appointment.model.Appointment;
import com.example.MedQueue.appointment.repo.AppointmentRepository;
import com.example.MedQueue.auth.models.AppUser;
import com.example.MedQueue.user.model.SystemSetting;
import com.example.MedQueue.user.repository.SystemSettingRepository;
import com.example.MedQueue.user.service.interfaces.AdminPanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class AdminPanelServiceImpl implements AdminPanelService {

//    private final AppointmentRepository appointmentRepository;
//    private final SystemSettingRepository settingRepository;
//
//
//    public List<AppUser> getAllUsers() {
//        return appUserRepository.findAll();
//    }
//
//    public AppUser updateUsers(UUID id, AppUser updatedUser) {
//        AppUser user = appUserRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        user.setFullName(updatedUser.getFullName());
//        user.setRoles(updatedUser.getRoles());
//        return appUserRepository.save(user);
//    }
//
//    public void deleteUser(UUID id) {
//        appUserRepository.deleteById(id);
//    }



}
