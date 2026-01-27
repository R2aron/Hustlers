package com.example.Hustlers.repository;

import com.example.Hustlers.model.Locations;
import com.example.Hustlers.model.Offer;
import com.example.Hustlers.model.ServicesCategorys;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Optional<Offer> findByHustlerId(UUID hustlerId);
    List<Offer> findAllByHustlerId(UUID hustlerId);
    Optional<List<Offer>> findByServicesCategoryAndLocation(ServicesCategorys category, Locations location);
}
