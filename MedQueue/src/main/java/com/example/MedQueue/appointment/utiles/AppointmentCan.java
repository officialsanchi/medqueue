package com.example.MedQueue.appointment.utiles;

import com.example.MedQueue.appointment.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class AppointmentCan {
    private String patientId;
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "appointment_status", joinColumns = @JoinColumn(name = "appointment_id"))
    @Column(name = "appointmentStatus")
    private AppointmentStatus status;
    public void cancel(String requestingPatientId) {
        if (!this.patientId.equals(requestingPatientId)) {
            throw new RuntimeException("Unauthorized cancel attempt.");
        }
        if (this.status == AppointmentStatus.CANCELLED) {
            throw new RuntimeException("Appointment already cancelled.");
        }
        this.status = AppointmentStatus.CANCELLED;
    }
}
