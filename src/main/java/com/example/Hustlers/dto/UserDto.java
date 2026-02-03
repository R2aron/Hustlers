package com.example.Hustlers.dto;

import com.example.Hustlers.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto implements AccountDto{
    private String firstname;
    private String lastname;
    private String email;

    public UserDto(User user)
    {
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
    }
}
