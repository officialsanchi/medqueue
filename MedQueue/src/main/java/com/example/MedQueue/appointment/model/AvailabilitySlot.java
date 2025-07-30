package com.example.MedQueue.appointment.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name="availability_slot")
@Getter
@Setter
@Builder

public class AvailabilitySlot {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String doctorId;
    private LocalDate date;
    
    @ElementCollection
    @CollectionTable(name = "availability_slot_times", joinColumns = @JoinColumn(name = "slot_id"))
    @Column(name = "available_time")
    private List<LocalTime> availableTimes;
}
