package com.example.Hustlers.service;

import com.example.Hustlers.dto.AccountDto;
import com.example.Hustlers.dto.UserDto;

import java.util.UUID;

public interface UserServiceInterface {
    public AccountDto getCurrentAccount();
    public UserDto updateUser(UUID userId, UserDto userDto)

}
