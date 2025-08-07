package com.example.MedQueue.user.repository;

import com.example.MedQueue.user.model.SystemSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SystemSettingRepository extends JpaRepository<SystemSetting, UUID> {
}
