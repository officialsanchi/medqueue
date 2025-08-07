package com.example.MedQueue.user.service.interfaces;

import com.example.MedQueue.appointment.model.Appointment;
import com.example.MedQueue.auth.models.AppUser;
import com.example.MedQueue.user.model.SystemSetting;

import java.util.List;
import java.util.UUID;

public interface AdminPanelService {
    List<AppUser> getAllUsers();
    AppUser updateUsers(UUID id, AppUser updatedUser);
    void deleteUser(UUID id);
    List<Appointment> getAllAppointments();
    SystemSetting getSettings();
    SystemSetting updateSettings(SystemSetting newSettings);
}
