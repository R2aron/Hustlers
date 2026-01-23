package com.example.Hustlers.repository;

import com.example.Hustlers.model.HustlerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HustlerRepository extends JpaRepository<HustlerProfile, UUID> {
    Optional<HustlerProfile> findByUserId(UUID userId);
}
