package com.example.MedQueue.notification.service.interfaces;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public interface NotificationService {

    String sendNotification( UUID id, UUID patientId,UUID doctorId, String descriptionOfPatientHealth,String patientEmail,String doctorEmail, String patientPhoneNumber, String doctorPhoneNumber);
    String sendNotificationToDoctor(UUID id,UUID doctorId,UUID patientId,String descriptionOfPatientHealth,String doctorEmail,String doctorPhoneNumber, LocalTime timeForAppoint, LocalDate dateForAppointment );
    String sendNotificationToPatient(UUID id,  UUID patientId,  String patientEmail, String patientPhoneNumber,LocalTime timeForAppoint, LocalDate dateForAppointment);
}
