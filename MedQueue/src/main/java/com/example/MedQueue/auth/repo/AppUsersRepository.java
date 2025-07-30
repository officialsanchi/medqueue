package com.example.MedQueue.auth.repo;

import com.example.MedQueue.auth.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUsersRepository extends JpaRepository<AppUser, UUID> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByPhoneNumber(String phoneNumber);

}
