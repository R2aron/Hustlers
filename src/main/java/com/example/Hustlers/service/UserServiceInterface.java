package com.example.Hustlers.service;

import com.example.Hustlers.dto.AccountDto;
import com.example.Hustlers.dto.UserDto;

public interface UserServiceInterface {
    public AccountDto getCurrentAccount();
//    public UserDto updateUser(UUID userId, UserDto userDto);
    public UserDto updateUser(UserDto userDto);
    public void deleteUser();
}
