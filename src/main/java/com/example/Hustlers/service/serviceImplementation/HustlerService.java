package com.example.Hustlers.service.serviceImplementation;

import com.example.Hustlers.dto.HustlerProfileDto;
import com.example.Hustlers.model.HustlerProfile;
import com.example.Hustlers.model.Role;
import com.example.Hustlers.model.User;
import com.example.Hustlers.repository.HustlerRepository;
import com.example.Hustlers.repository.UserRepository;
import com.example.Hustlers.service.HustlerServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HustlerService implements HustlerServiceInterface {

    private final HustlerRepository hustlerRepository;
    private final UserRepository userRepository;

    public HustlerProfileDto createHustlerProfile(UUID userId, HustlerProfileDto dto)
    {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        HustlerProfile hustlerProfile = new HustlerProfile(dto);
        user.setHustlerProfile(hustlerProfile);
        user.setRole(Role.HUSTLER);
        userRepository.save(user);
        return new HustlerProfileDto(hustlerProfile);
    }

    public HustlerProfile findbyId(UUID hustlerId)
    {
        return hustlerRepository.findById(hustlerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Hustler not found"));
    }

    // de revizuit
    public HustlerProfileDto getHustlerProfile(UUID userId)
    {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));

        HustlerProfile hustlerProfile = user.getHustlerProfile();
        return new HustlerProfileDto(hustlerProfile);

    }



    public void deleteHustlerProfile(UUID userId)
    {
        HustlerProfile hustlerProfile = hustlerRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"The user does not have this type of profile"));
        hustlerRepository.delete(hustlerProfile);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
        user.setHustlerProfile(null);
        user.setRole(Role.USER);
        userRepository.save(user);


    }

    public HustlerProfileDto findHustlerById(UUID hustlerId)
    {
        HustlerProfile hustlerProfile = hustlerRepository.findById(hustlerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hustler not found"));
        return new HustlerProfileDto(hustlerProfile);

    }
}
