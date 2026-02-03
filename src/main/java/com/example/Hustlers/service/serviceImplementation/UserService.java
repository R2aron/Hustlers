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

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    //de facut metoda pentru hustler si user
    private final UserRepository userRepository;
    private final HustlerRepository hustlerRepository;

//    @Override
//    public UserDto getCurrentUser() {
//            String email = SecurityContextHolder.getContext().getAuthentication().getName();
//            User user = userRepository.findByEmail(email)
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
//            return new UserDto(user);
//        }

    public AccountDto getCurrentAccount()
    {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        switch (user.getRole())
        {
            case USER:
                return new UserDto(user);
            case HUSTLER:
                HustlerProfile hustlerProfile = hustlerRepository.findByUser(user);
                return new HustlerProfileDto(hustlerProfile);
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user role");
        }
    }

    }
