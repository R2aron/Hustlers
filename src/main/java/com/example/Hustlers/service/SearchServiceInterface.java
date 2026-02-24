package com.example.Hustlers.service;

import com.example.Hustlers.dto.OfferDto;
import com.example.Hustlers.dto.SearchCriteria;
import com.example.Hustlers.model.Locations;
import com.example.Hustlers.model.ServicesCategorys;

import java.util.List;

public interface SearchServiceInterface {
    List<OfferDto> findOffersByCategoryLocation(ServicesCategorys category, Locations location);
    String normalizeParam(String param);
    List<OfferDto> search(SearchCriteria c);

}
