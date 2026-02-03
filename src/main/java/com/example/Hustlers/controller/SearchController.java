package com.example.Hustlers.controller;

import com.example.Hustlers.dto.OfferDto;
import com.example.Hustlers.dto.SearchCriteria;
import com.example.Hustlers.model.Locations;
import com.example.Hustlers.model.ServicesCategorys;
import com.example.Hustlers.service.SearchServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home/search/services")//de pus calea
@RequiredArgsConstructor
public class SearchController {

    private final SearchServiceInterface searchService;

    @GetMapping("/searchByCategoryAndLocation")//cred ca ar trebui sa fac un dto care sa contina si alte informatii relevante pe langa serviciu, ex: hustler.
    public ResponseEntity<List<OfferDto>> getSearchByCategoryandLocation(@RequestParam ServicesCategorys category, @RequestParam Locations location)
    {
        return ResponseEntity.ok(searchService.findOffersByCategoryLocation(category, location));
    }

    @GetMapping("/generalServicesSearch")
    public ResponseEntity<List<OfferDto>> generalSearch(@ParameterObject SearchCriteria criteria)
    {
        return ResponseEntity.ok(searchService.search(criteria));
    }
}
