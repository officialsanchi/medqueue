package com.example.MedQueue.queue.controller;

import com.example.MedQueue.notification.service.QueueNotifierService;
import com.example.MedQueue.queue.service.implementations.QueueServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/queue")
@RequiredArgsConstructor
public class QueueController {
    private final QueueServiceImpl queueService;
    private final QueueNotifierService queueNotifierService;

    @PostMapping("/add")
    public ResponseEntity<?> joinQueue(@RequestParam String doctorId,
                                       @RequestParam String patientId,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        queueService.addToQueue(doctorId, patientId, date);
        return ResponseEntity.ok("Added to queue");
    }

    @PostMapping("/next")
    public ResponseEntity<?> nextPatient(@RequestParam String doctorId,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        queueService.nextPatient(doctorId, date);
        return ResponseEntity.ok("Next patient called");
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<?> cancelQueue(@RequestParam String doctorId,
                                         @RequestParam String patientId,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        queueService.cancelFromQueue(patientId, doctorId, date);
        return ResponseEntity.ok("Cancelled from queue");
    }


    @PostMapping("/notify-next")
    public ResponseEntity<String> notifyNext(@RequestParam String doctorId) {
        queueNotifierService.notifyNextPatient(doctorId, LocalDate.now());
        return ResponseEntity.ok("Notifications sent to upcoming patients.");
    }
}
