package com.example.Hustlers.dto;

import com.example.Hustlers.model.Offer;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferImageDto {

    private String imageFileName;
    private String imageType;
    @Lob
    private byte[] imageData;

    public OfferImageDto(Offer.Image dto)
    {
        this.imageData = dto.getImageData();
        this.imageFileName = dto.getImageFileName();
        this.imageType = dto.getImageType();
    }

}
