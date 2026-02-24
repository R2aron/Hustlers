package com.example.Hustlers.dto;

import com.example.Hustlers.model.Locations;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestHustlerProfileDto (
    @NotBlank
    String fullname,
    @NotBlank
    String displayName,
    @Email
    String email,
    @NotBlank
    String companyName,
    @NotBlank
    String phoneNumber,
    String description,
    @Enumerated(EnumType.STRING)
    Locations location
){}

