package com.example.MedQueue.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class EmailService {
    private final JavaMailSender mailSender;

    public void sendQueueNotification(String toEmail, String patientName, int position) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("You're up soon for your appointment");
        message.setText("Hi " + patientName + ", you're number " + position + " in the queue. Please be ready.");
        mailSender.send(message);
    }

}
