package com.example.MedQueue.queue.service.implementations;

import com.example.MedQueue.queue.model.QueueEntry;
import com.example.MedQueue.queue.repos.QueueRepository;
import com.example.MedQueue.queue.service.interfaces.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor

public class QueueServiceImpl implements QueueService {
    private final QueueRepository queueRepo;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void addToQueue(String doctorId, String patientId, LocalDate date) {
        int position = queueRepo.findByDoctorIdAndDateOrderByPosition(doctorId, date).size() + 1;
        QueueEntry entry = new QueueEntry();
        entry.setDoctorId(doctorId);
        entry.setPatientId(patientId);
        entry.setDate(date);
        entry.setPosition(position);
        queueRepo.save(entry);
        notifyQueueUpdate(doctorId, date);
    }

    @Override
    public void nextPatient(String doctorId, LocalDate date) {
        QueueEntry current = queueRepo.findFirstByDoctorIdAndDateAndActiveTrueOrderByPosition(doctorId, date)
                .orElseThrow(() -> new RuntimeException("No patient in queue."));
        current.setActive(false);
        queueRepo.save(current);

        QueueEntry next = queueRepo.findFirstByDoctorIdAndDateAndActiveTrueOrderByPosition(doctorId, date).orElse(null);
        if (next != null) {
            messagingTemplate.convertAndSend("/topic/queue/" + doctorId + "/" + date, "Next: " + next.getPatientId());
        }

        notifyQueueUpdate(doctorId, date);
    }
    @Override

    public void cancelFromQueue(String patientId, String doctorId, LocalDate date) {
        List<QueueEntry> queue = queueRepo.findByDoctorIdAndDateOrderByPosition(doctorId, date);
        QueueEntry entry = queue.stream()
                .filter(e -> e.getPatientId().equals(patientId))
                .findFirst().orElseThrow(() -> new RuntimeException("Patient not found in queue."));
        queueRepo.delete(entry);
        resequenceQueue(queue);
        notifyQueueUpdate(doctorId, date);
    }

    private void resequenceQueue(List<QueueEntry> queue) {
        int pos = 1;
        for (QueueEntry e : queue) {
            e.setPosition(pos++);
            queueRepo.save(e);
        }
    }

    private void notifyQueueUpdate(String doctorId, LocalDate date) {
        List<QueueEntry> updatedQueue = queueRepo.findByDoctorIdAndDateOrderByPosition(doctorId, date);
        messagingTemplate.convertAndSend("/topic/queue/" + doctorId + "/" + date, updatedQueue);
    }
}
