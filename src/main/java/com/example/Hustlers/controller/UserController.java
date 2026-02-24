package com.example.Hustlers.controller;

import com.example.Hustlers.dto.EmailDetails;
import com.example.Hustlers.dto.UserDto;
import com.example.Hustlers.service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceInterface userService;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('HUSTLER')")
    @GetMapping("/me")
    public ResponseEntity<?> me()
    {
        return ResponseEntity.ok(userService.getCurrentAccount());
    }

    @PreAuthorize("hasAuthority('USER')")//+Admin cand e gata
    @PatchMapping("/update")
    public ResponseEntity<UserDto> updateUser(UserDto dto)
    {
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    @PreAuthorize(("hasAuthority('User')"))
    @GetMapping("/delete")
    public ResponseEntity<Void> deleteUser()
    {
        userService.deleteUser();
        return ResponseEntity.noContent().build();
    }

}
