package com.example.Hustlers.dto;

import com.example.Hustlers.model.HustlerProfile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HustlerProfileDto implements AccountDto{

    @NotBlank(message = "Field cannot be empty")
    private String fullname;
    @NotBlank(message = "Field cannot be empty")
    private String displayName;
    @Email(message = "Field should look like : john@gmail.com")
    private String email;
    @NotBlank(message = "Field cannot be empty")
    private String companyName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String description;

    public HustlerProfileDto(HustlerProfile hustlerProfile)
    {
        this.fullname = hustlerProfile.getFullname();
        this.displayName = hustlerProfile.getDisplayName();
        this.email = hustlerProfile.getEmail();
        this.companyName = hustlerProfile.getCompanyName();
        this.phoneNumber = hustlerProfile.getPhoneNumber();
        this.description = hustlerProfile.getDescription();
    }
}
