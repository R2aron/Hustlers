package com.example.Hustlers.service;

import com.example.Hustlers.dto.OfferDto;
import com.example.Hustlers.dto.SearchCriteria;
import com.example.Hustlers.model.Locations;
import com.example.Hustlers.model.ServicesCategorys;

import java.util.List;

public interface SearchServiceInterface {
    public List<OfferDto> findOffersByCategoryLocation(ServicesCategorys category, Locations location);
    public List<OfferDto> search(SearchCriteria c);

}
