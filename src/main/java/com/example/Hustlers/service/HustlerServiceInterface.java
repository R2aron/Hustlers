package com.example.Hustlers.service;

import com.example.Hustlers.dto.HustlerProfileDto;

import java.util.UUID;

public interface HustlerServiceInterface {
    public HustlerProfileDto createHustlerProfile(UUID userId, HustlerProfileDto dto);
    public HustlerProfileDto getHustlerProfile(UUID userId);
    public void deleteHustlerProfile(UUID hustlerId);
    public HustlerProfileDto findHustlerById(UUID hustlerId);
}
