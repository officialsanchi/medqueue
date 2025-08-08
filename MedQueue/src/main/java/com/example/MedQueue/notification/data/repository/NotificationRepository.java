package com.example.MedQueue.notification.data.repository;

import com.example.MedQueue.notification.data.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    Notification findByDoctorPhoneNumber(String doctorPhoneNumber);
    Notification findByDoctorEmail(String doctorEmail);
    Notification findByPatientPhoneNumber(String patientPhoneNumber);
    Notification findByPatientEmail(String patientEmail);
    Notification findByPatientId(UUID patientId);
    Notification findByDoctorId(UUID doctorId);


}
