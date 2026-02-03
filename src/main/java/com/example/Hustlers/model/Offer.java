package com.example.Hustlers.model;

import com.example.Hustlers.dto.OfferDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Services")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String approximateDuration;
    private Float rating;
    private String review;
    @Enumerated(EnumType.STRING)
    private ServicesCategorys servicesCategory;
    @Enumerated(EnumType.STRING)
    private Locations location;
    //pictures
    // Boolean active


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hustler_id", nullable = false)
    private HustlerProfile hustler;//cred ca de aici scoate hustlerul in afisarea setului de servicii.

    public Offer (OfferDto dto)
    {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.price = dto.getPrice();
        this.approximateDuration = dto.getApproximateDuration();
        this.servicesCategory = dto.getServicesCategory();
        this.location = dto.getLocation();
    }
}
