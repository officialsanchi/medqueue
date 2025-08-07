package com.example.MedQueue.queue.repos;

import com.example.MedQueue.queue.model.QueueEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QueueRepository extends JpaRepository<QueueEntry, UUID> {
    List<QueueEntry> findByDoctorIdAndDateOrderByPosition(String doctorId, LocalDate date);
    Optional<QueueEntry> findFirstByDoctorIdAndDateAndActiveTrueOrderByPosition(String doctorId, LocalDate date);
}
