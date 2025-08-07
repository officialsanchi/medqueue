package com.example.MedQueue.appointment.model;

import com.example.MedQueue.appointment.enums.AppointmentStatus;
import com.example.MedQueue.auth.enums.Role;
import com.google.auto.value.AutoValue;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="appointment")


public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String patientId;
    private String doctorId;
    private String description;
    private LocalDate date;
    private LocalTime time;
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "appointment_status", joinColumns = @JoinColumn(name = "appointment_id"))
    @Column(name = "appointmentStatus")
    private AppointmentStatus status;
}
