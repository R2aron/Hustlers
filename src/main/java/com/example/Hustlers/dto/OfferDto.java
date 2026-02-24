package com.example.Hustlers.dto;

import com.example.Hustlers.model.Locations;
import com.example.Hustlers.model.Offer;
import com.example.Hustlers.model.ServicesCategorys;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

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
    private LinkedHashSet<MultipartFile> imageSet= new LinkedHashSet<>();

    public OfferDto(Offer offer) {
//        super(OfferImageDto(offer));
        this.name = offer.getName();
        this.description = offer.getDescription();
        this.price = offer.getPrice();
        this.approximateDuration = offer.getApproximateDuration();
        this.servicesCategory = offer.getServicesCategory();
        this.location = offer.getLocation();
//        this.imageSet = offer.getOfferImagesSet().stream()
//                .map(OfferImageDto::new)
//                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
