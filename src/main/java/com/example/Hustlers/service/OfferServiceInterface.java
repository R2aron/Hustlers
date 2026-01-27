package com.example.Hustlers.service;

import com.example.Hustlers.dto.OfferDto;

import java.util.List;
import java.util.UUID;

public interface OfferServiceInterface {
    public OfferDto createOffer(UUID hustlerId, OfferDto dto);
    public OfferDto getOffer(UUID hustlerId);
    public List<OfferDto> getAllOffers(UUID hustlerId);
    public void deleteOffer(UUID hustlerId,Integer serviceId);
}
