package com.example.MedQueue.queue.service.interfaces;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface QueueService {
    void nextPatient(String doctorId, LocalDate date);
    void cancelFromQueue(String patientId, String doctorId, LocalDate date);
    void addToQueue(String doctorId, String patientId, LocalDate date);
}
