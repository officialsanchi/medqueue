package com.example.MedQueue.user.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name="system_setting")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SystemSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int maxQueueSize;
    private boolean emergencyModeEnabled;
}
