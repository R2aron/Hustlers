package com.example.Hustlers.service.serviceImplementation;

import com.example.Hustlers.dto.AccountDto;
import com.example.Hustlers.dto.HustlerProfileDto;
import com.example.Hustlers.dto.UserDto;
import com.example.Hustlers.model.HustlerProfile;
import com.example.Hustlers.model.User;
import com.example.Hustlers.repository.HustlerRepository;
import com.example.Hustlers.repository.UserRepository;
import com.example.Hustlers.service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final HustlerRepository hustlerRepository;


    @Override
    public AccountDto getCurrentAccount() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        switch (user.getRole()) {
            case USER:
                return new UserDto(user);
            case HUSTLER:
                HustlerProfile hustlerProfile = hustlerRepository.findByUser(user);
                return new HustlerProfileDto(hustlerProfile);
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user role");
        }
    }

    @Override
    public UserDto updateUser(UUID userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (userDto.getFirstname() != null) user.setFirstname(userDto.getFirstname());
        if (userDto.getLastname() != null) user.setLastname(userDto.getLastname());
        if (userDto.getEmail() != null) user.setEmail(userDto.getEmail());

        userRepository.save(user);
        return new UserDto(user);

    }
}
