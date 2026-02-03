package com.example.Hustlers.repository;

import com.example.Hustlers.dto.SearchCriteria;
import com.example.Hustlers.model.Locations;
import com.example.Hustlers.model.Offer;
import com.example.Hustlers.model.ServicesCategorys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Optional<Offer> findByHustlerId(UUID hustlerId);
    List<Offer> findAllByHustlerId(UUID hustlerId);
    Optional<List<Offer>> findByServicesCategoryAndLocation(ServicesCategorys category, Locations location);

    @Query("""
        SELECT o FROM Offer o
        WHERE (:name IS NULL OR LOWER(o.name) LIKE LOWER(CONCAT('%', :name, '%')))
          AND (:location IS NULL OR o.location = :location)
          AND (:servicesCategory IS NULL OR o.servicesCategory = :servicesCategory)
          AND (:minPrice IS NULL OR o.price >= :minPrice)
          AND (:maxPrice IS NULL OR o.price <= :maxPrice)
    """)
    List<Offer> search(
            @Param("name") String name,
            @Param("location") Locations location,
            @Param("servicesCategory") ServicesCategorys servicesCategory,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );

}
