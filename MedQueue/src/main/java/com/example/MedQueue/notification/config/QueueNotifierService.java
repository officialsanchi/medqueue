package com.example.MedQueue.notification.config;

import com.example.MedQueue.appointment.model.Appointment;
import com.example.MedQueue.appointment.repo.AppointmentRepository;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueueNotifierService {
    private final AppointmentRepository appointmentRepo;
    private final EmailService emailService;
    private final SmsService smsService;
    private final PushNotificationService pushNotificationService;

//    public void notifyNextPatient(String doctorId, LocalDate today) {
//        List<Appointment> appointments = appointmentRepo.findByDoctorIdAndDate(doctorId, today);
//
//
//        for (int i = 1; i <= 2 && i < appointments.size(); i++) {
//            Appointment appt = appointments.get(i);
//            String email = appt.getPatientEmail(); // Assume this exists
//            String phone = appt.getPatientPhoneNumber();
//            String fcmToken = appt.getFmcToken();
//
//            emailService.sendQueueNotification(email, appt.getPatientName(), i + 1);
//            smsService.sendQueueAlert(phone, i + 1);
//            try {
//                pushNotificationService.sendPushNotification(fcmToken, "You're up soon!", "Please be ready, you're number " + (i + 1));
//            } catch (FirebaseMessagingException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
