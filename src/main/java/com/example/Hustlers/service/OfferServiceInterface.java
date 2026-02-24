package com.example.Hustlers.service;

import com.example.Hustlers.dto.OfferDto;
import com.example.Hustlers.dto.RequestOfferDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

public interface OfferServiceInterface {
//    public OfferDto createOffer(UUID hustlerId, OfferDto dto);

    OfferDto createOffer(UUID hustlerId, OfferDto dto, LinkedHashSet<MultipartFile> imagesFiles);


    OfferDto getOffer(UUID hustlerId);

    List<OfferDto> getAllOffers(UUID hustlerId);

    void deleteOffer(UUID hustlerId,Integer serviceId);

//    public OfferDto update(UUID hustlerId, Integer serviceId, OfferDto dto);

    OfferDto update(UUID hustlerId, Integer serviceId, RequestOfferDto dto);
}
