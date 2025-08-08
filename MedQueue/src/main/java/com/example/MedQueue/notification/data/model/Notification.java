package com.example.MedQueue.notification.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private UUID patientId;
    private UUID doctorId;
    private String descriptionOfPatientHealth;
    private String patientEmail;
    private String doctorEmail;
    private String doctorPhoneNumber;
    private String patientPhoneNumber;
    private LocalTime timeForAppoint;
   private  LocalDate dateForAppointment;


}
