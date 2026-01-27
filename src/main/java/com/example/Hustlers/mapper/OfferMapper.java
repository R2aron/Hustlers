package com.example.Hustlers.mapper;

import com.example.Hustlers.dto.OfferDto;
import com.example.Hustlers.model.Offer;

import java.util.List;
import java.util.stream.Collectors;

public class OfferMapper {

    public static List<OfferDto> toDtoList(List<Offer> offerList)
    {
        if(offerList == null || offerList.isEmpty())
            return List.of();
        List<OfferDto> offerDtoList = offerList.stream()
                .map(offer -> {
                OfferDto dto = new OfferDto(offer);
                return dto;
                }).collect(Collectors.toList());
        return offerDtoList;
    }
}
