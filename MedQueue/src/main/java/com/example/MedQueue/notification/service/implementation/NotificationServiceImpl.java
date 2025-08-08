package com.example.MedQueue.notification.service.implementation;

import com.example.MedQueue.doctor.data.repository.DoctorRepository;
import com.example.MedQueue.notification.data.model.Notification;
import com.example.MedQueue.notification.data.repository.NotificationRepository;
import com.example.MedQueue.notification.service.interfaces.NotificationService;
import com.example.MedQueue.patient.data.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
@Service
@RequiredArgsConstructor

public class NotificationServiceImpl implements NotificationService {
    private  final NotificationRepository notificationRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Override
    public String sendNotification(UUID id, UUID patientId, UUID doctorId, String descriptionOfPatientHealth, String patientEmail, String doctorEmail, String patientPhoneNumber, String doctorPhoneNumber) {

        Notification notification = Notification.builder()
                .id( id )
                .descriptionOfPatientHealth( descriptionOfPatientHealth )
                .doctorEmail( doctorEmail )
                .patientEmail( patientEmail )
                .doctorPhoneNumber( doctorPhoneNumber )
                .patientPhoneNumber( patientPhoneNumber )
                .doctorId( doctorId )
                .patientId( patientId )
                .build();
        notificationRepository.save(notification);

        return "";
    }



    @Override
    public String sendNotificationToPatient(UUID id, UUID patientId, String patientEmail, String patientPhoneNumber, LocalTime timeForAppoint, LocalDate dateForAppointment) {
        Notification notification = Notification.builder()
                .id(id)
                .patientId(patientId)
                .patientEmail(patientEmail)
                .patientPhoneNumber(patientPhoneNumber)
                .timeForAppoint(timeForAppoint.plusHours(2))
                .dateForAppointment(dateForAppointment.minusDays(2))
                .build();
        notificationRepository.save(notification);
        return "";
    }

    @Override
    public String sendNotificationToDoctor(UUID id, UUID doctorId, UUID patientId, String descriptionOfPatientHealth, String doctorEmail, String doctorPhoneNumber,LocalTime timeForAppoint, LocalDate dateForAppointment) {
        Notification notification = Notification.builder()
                .id( id )
                .patientId( patientId )
                .doctorId( doctorId )
                .doctorPhoneNumber( doctorPhoneNumber )
                .doctorEmail( doctorEmail )
                .descriptionOfPatientHealth( descriptionOfPatientHealth )
                .dateForAppointment( dateForAppointment.minusDays( 2 ) )
                .timeForAppoint( timeForAppoint.plusHours( 2 ) )
                .build();
            notificationRepository.save(  notification );
        return "";
    }
}
