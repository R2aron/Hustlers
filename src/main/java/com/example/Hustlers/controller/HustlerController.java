package com.example.Hustlers.controller;

import com.example.Hustlers.dto.HustlerProfileDto;
import com.example.Hustlers.service.HustlerServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/hustlers")
@RequiredArgsConstructor
public class HustlerController {

    private final HustlerServiceInterface hustlerService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/{userId}/createProfile")
    public ResponseEntity<HustlerProfileDto> createHustler(@PathVariable UUID userId, @Valid @RequestBody HustlerProfileDto dto)
    {
        return ResponseEntity.ok(hustlerService.createHustlerProfile(userId, dto));
    }

    @PreAuthorize("hasAuthority('HUSTLER')")
    @GetMapping("/{userId}/getHustlerByUserId")
    public ResponseEntity<HustlerProfileDto> findHustlerProfileByUserId(@PathVariable UUID userId)
    {
        return ResponseEntity.ok(hustlerService.getHustlerProfile(userId));
    }

    @PreAuthorize("hasAuthority('HUSTLER')")
    @DeleteMapping("/{userId}/deleteHustlerProfile")
    public ResponseEntity<Void> deletehustlerProfile(@PathVariable UUID userId)
    {
        hustlerService.deleteHustlerProfile(userId);
        return ResponseEntity.noContent().build();
    }


}
