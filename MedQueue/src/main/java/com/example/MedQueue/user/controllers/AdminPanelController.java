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


}
