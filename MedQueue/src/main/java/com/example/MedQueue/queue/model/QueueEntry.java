package com.example.MedQueue.queue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name="queue_entry")
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueueEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String doctorId;
    private String patientId;
    private LocalDate date;
    private int position;
    private boolean active = true;
}
