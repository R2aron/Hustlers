package com.example.Hustlers.service;

import com.example.Hustlers.dto.HustlerProfileDto;
import com.example.Hustlers.dto.RequestHustlerProfileDto;

import java.util.UUID;

public interface HustlerServiceInterface {
    public HustlerProfileDto createHustlerProfile(UUID userId, HustlerProfileDto dto);
    public HustlerProfileDto getHustlerProfile(UUID userId);
    public void deleteHustlerProfile(UUID hustlerId);
    public HustlerProfileDto findHustlerById(UUID hustlerId);
    public HustlerProfileDto update(UUID userId, UUID hustlerId, RequestHustlerProfileDto dto);
}
