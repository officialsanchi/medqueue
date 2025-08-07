package com.example.MedQueue.user.controllers;

import com.example.MedQueue.appointment.model.Appointment;
import com.example.MedQueue.auth.models.AppUser;
import com.example.MedQueue.user.model.SystemSetting;
import com.example.MedQueue.user.service.implementation.AdminPanelServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminPanelController {
    private final AdminPanelServiceImpl adminPanelService;

    @GetMapping("/users")
    public List<AppUser> getAllUsers() {
        return adminPanelService.getAllUsers();
    }

    @PutMapping("/users/{id}")
    public AppUser updateUser(@PathVariable UUID id, @RequestBody AppUser user) {
        return adminPanelService.updateUsers(id, user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable UUID id) {
        adminPanelService.deleteUser(id);
    }

    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return adminPanelService.getAllAppointments();
    }

    @GetMapping("/settings")
    public SystemSetting getSystemSettings() {
        return adminPanelService.getSettings();
    }

    @PutMapping("/settings")
    public SystemSetting updateSystemSettings(@RequestBody SystemSetting setting) {
        return adminPanelService.updateSettings(setting);
    }
}
