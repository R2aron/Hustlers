package com.example.Hustlers.dto;

import com.example.Hustlers.model.Offer;
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

    public OfferDto(Offer offer) {
        this.name = offer.getName();
        this.description = offer.getDescription();
        this.price = offer.getPrice();
    }
}
