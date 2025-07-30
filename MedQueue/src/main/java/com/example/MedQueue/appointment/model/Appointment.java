package com.example.MedQueue.appointment.model;

import com.example.MedQueue.appointment.enums.AppointmentStatus;
import com.example.MedQueue.auth.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name="appointment")
@Getter
@Setter
@Builder

public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String patientId;
    private String doctorId;

    private LocalDate date;
    private LocalTime time;


    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "appointment_status", joinColumns = @JoinColumn(name = "appointment_id"))
    @Column(name = "appointmentStatus")
    private AppointmentStatus status;
}
