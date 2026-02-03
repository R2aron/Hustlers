package com.example.Hustlers.service.serviceImplementation;

import com.example.Hustlers.dto.OfferDto;
import com.example.Hustlers.dto.SearchCriteria;
import com.example.Hustlers.mapper.OfferMapper;
import com.example.Hustlers.model.Locations;
import com.example.Hustlers.model.ServicesCategorys;
import com.example.Hustlers.repository.OfferRepository;
import com.example.Hustlers.service.SearchServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.tomcat.util.http.RequestUtil.normalize;


@Service
@RequiredArgsConstructor
public class SearchService implements SearchServiceInterface {
    public final OfferRepository offerRepository;

    public List<OfferDto> findOffersByCategoryLocation(ServicesCategorys category, Locations location){
        if(category.ordinal() >= 0  && location.ordinal() >= 0)
        {
            List<OfferDto> offerDtoList = OfferMapper.toDtoList(offerRepository.findByServicesCategoryAndLocation(category, location)
                    .orElseThrow(() -> new RuntimeException("Search not found")));
            return offerDtoList;
        }
        return null;

    }


private String normalizeParam(String param) {
    return (param == null || param.isBlank()) ? null : param.trim().toLowerCase();
}

public List<OfferDto> search(SearchCriteria criteria)
{
    return OfferMapper.toDtoList(offerRepository.search(
            normalizeParam(criteria.name()),
            criteria.location(),
            criteria.servicesCategory(),//de normaliat si enumurile
            criteria.minPrice(),
            criteria.maxPrice()
    ));

}

}
