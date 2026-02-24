package com.example.Hustlers.dto;

import com.example.Hustlers.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto implements AccountDto{
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @Email
    private String email;

    public UserDto(User user)
    {
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
    }
}
