package com.example.Hustlers.controller;

import com.example.Hustlers.dto.OfferDto;
import com.example.Hustlers.service.OfferServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hustlers/{hustlerId}/offers")
@RequiredArgsConstructor
public class HustlerOfferController {

    private final OfferServiceInterface offerService;

    @PostMapping("/create")
    public ResponseEntity<OfferDto> createOffer(@PathVariable UUID hustlerId, @Valid @RequestBody OfferDto dto)
    {
        return ResponseEntity.ok(offerService.createOffer(hustlerId, dto));
    }

    @GetMapping
    public ResponseEntity<List<OfferDto>> getAllOffersByHustlerId(@PathVariable UUID hustlerId)
    {
        return ResponseEntity.ok(offerService.getAllOffers(hustlerId));
    }

    @DeleteMapping("/{serviceId}/delete")
    public ResponseEntity<Void> deleteOffer(@PathVariable UUID hustlerId, @PathVariable Integer serviceId)
    {
        offerService.deleteOffer(hustlerId, serviceId);
        return ResponseEntity.noContent().build();
    }
}
