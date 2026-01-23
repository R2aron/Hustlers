package com.example.Hustlers.model;


import com.example.Hustlers.dto.HustlerProfileDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "hustlers")
public class HustlerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String fullname;
    private String displayName;
    private String email;
    private String companyName;
    private String phoneNumber;
    private String description;
    private Double rating;
    private Boolean isActive;
//    private String location;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToMany(mappedBy = "hustler", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Offer> serviceCatalog = new LinkedHashSet<>();

    public HustlerProfile(HustlerProfileDto dto)
    {
        this.fullname = dto.getFullname();
        this.displayName = dto.getDisplayName();
        this.email = dto.getEmail();
        this.companyName = dto.getCompanyName();
        this.phoneNumber = dto.getPhoneNumber();
        this.description = dto.getDescription();
    }

}
