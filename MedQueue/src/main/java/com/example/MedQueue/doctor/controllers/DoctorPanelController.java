package com.example.MedQueue.doctor.controllers;

import com.example.MedQueue.appointment.enums.AppointmentStatus;
import com.example.MedQueue.appointment.model.Appointment;
import com.example.MedQueue.doctor.service.interfaces.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorPanelController {
//    private final DoctorService doctorPanelService;

//    @GetMapping("/{doctorId}/appointments/today")
//    public List<Appointment> getTodaysAppointments(@PathVariable UUID doctorId) {
//        return doctorPanelService.getTodaysAppointments(doctorId);
//    }
//
//    @GetMapping("/patients/{patientId}/history")
//    public List<Appointment> getPatientHistory(@PathVariable UUID patientId) {
//        return doctorPanelService.getPatientHistory(patientId);
//    }
//
//    @PutMapping("/appointments/{appointmentId}/status")
//    public Appointment updateStatus(@PathVariable UUID appointmentId,
//                                    @RequestParam AppointmentStatus status) {
//        return doctorPanelService.markAppointmentStatus(appointmentId, status);
//    }
}
