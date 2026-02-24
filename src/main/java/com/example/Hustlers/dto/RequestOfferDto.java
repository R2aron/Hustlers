package com.example.Hustlers.dto;

import com.example.Hustlers.model.Locations;
import com.example.Hustlers.model.ServicesCategorys;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RequestOfferDto(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotNull(message = "Price is required")
        @Positive
        Double price,
        String approximateDuration,
        @Enumerated(EnumType.STRING)
        ServicesCategorys servicesCategory,
        @Enumerated(EnumType.STRING)
        Locations location
) {
}
