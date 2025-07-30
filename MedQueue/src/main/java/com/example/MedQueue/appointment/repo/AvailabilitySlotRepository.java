package com.example.MedQueue.appointment.repo;

import com.example.MedQueue.appointment.model.AvailabilitySlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvailabilitySlotRepository extends JpaRepository<AvailabilitySlot, UUID> {
    Optional<AvailabilitySlot> findByDoctorIdAndDate(String doctorId, LocalDate date);
}
