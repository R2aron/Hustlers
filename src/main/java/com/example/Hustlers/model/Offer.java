package com.example.Hustlers.model;

import com.example.Hustlers.dto.OfferDto;
import com.example.Hustlers.dto.OfferImageDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashSet;

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
    // Boolean active
    private LinkedHashSet<Image> offerImagesSet;
//    private LinkedHashSet<MultipartFile> offerImageSet1;


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

    public void addImage(Image image)
    {
        offerImagesSet.add(image);
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Image {
        private String imageFileName;
        private String imageType;
        @Lob
        private byte[] imageData;

        public Image(OfferImageDto dto)
        {
            this.imageData = dto.getImageData();
            this.imageType = dto.getImageType();
            this.imageFileName = dto.getImageFileName();
        }

        public Image(MultipartFile multipartFile) throws IOException {
            this.imageData = multipartFile.getBytes();
            this.imageType = multipartFile.getContentType();
            this.imageFileName = multipartFile.getOriginalFilename();
        }
    }
}
