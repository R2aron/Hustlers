package com.example.Hustlers.service;

import com.example.Hustlers.dto.AuthenticationRequest;
import com.example.Hustlers.dto.AuthenticationResponse;
import com.example.Hustlers.dto.EmailDetails;
import com.example.Hustlers.dto.RegisterRequest;
import com.example.Hustlers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.Hustlers.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Hustlers.model.Role;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailServiceInterface emailService;

    public AuthenticationResponse register (RegisterRequest request)
    {
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(request.getEmail());
        emailDetails.setSubject("Welcome to Hustlers!");
//        emailDetails.setAttachment("C:\\Users\\Aron\\HustlersResources\\Hustlers community welcome message.png");
        emailDetails.setMsgBody("Dear " + request.getFirstname() + ",\n\nThank you for registering on Hustlers. We're excited to have you on board!\n\nBest regards,\nThe Hustlers Team");
        emailService.sendSimpleMail(emailDetails);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate (AuthenticationRequest request)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
