package com.example.Hustlers.dto;

import com.example.Hustlers.model.Locations;
import com.example.Hustlers.model.ServicesCategorys;

public record SearchCriteria(
        String name,
        Locations location,
        ServicesCategorys servicesCategory,
        Double minPrice,
        Double maxPrice
) {
}
