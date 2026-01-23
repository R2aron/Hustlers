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
    private int id;
    private String name;
    private String description;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hustler_id", nullable = false)
    private HustlerProfile hustler;

    public Offer (OfferDto dto)
    {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.price = dto.getPrice();
    }
}
