package com.example.Hustlers.dto;

import com.example.Hustlers.model.Locations;
import com.example.Hustlers.model.Offer;
import com.example.Hustlers.model.ServicesCategorys;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OfferDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull(message = "Price is required")
    @Positive
    private Double price;
    private String approximateDuration;
    @Enumerated(EnumType.STRING)
    private ServicesCategorys servicesCategory;
    @Enumerated(EnumType.STRING)
    private Locations location;

    public OfferDto(Offer offer) {
        this.name = offer.getName();
        this.description = offer.getDescription();
        this.price = offer.getPrice();
        this.approximateDuration = offer.getApproximateDuration();
        this.servicesCategory = offer.getServicesCategory();
        this.location = offer.getLocation();
    }
}
