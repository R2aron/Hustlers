package com.example.Hustlers.controller;

import com.example.Hustlers.dto.UserDto;
import com.example.Hustlers.service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceInterface userService;

    @GetMapping("/me")
    public ResponseEntity<?> me()
    {
        return ResponseEntity.ok(userService.getCurrentAccount());
    }
}
