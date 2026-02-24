package com.example.Hustlers.controller;

import com.example.Hustlers.dto.OfferDto;
import com.example.Hustlers.dto.RequestOfferDto;
import com.example.Hustlers.service.OfferServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/offers/{hustlerId}")
@RequiredArgsConstructor
public class HustlerOfferController {

    private final OfferServiceInterface offerService;

    @PreAuthorize("hasAuthority('HUSTLER')")
    @Operation(summary = "Create offer with images (multipart)")
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createOffer(@RequestPart UUID hustlerId, @Valid @RequestPart OfferDto dto, @RequestPart(value = "images", required = false) LinkedHashSet<MultipartFile> imagesFile)
    {
        try {
            return ResponseEntity.ok(offerService.createOffer(hustlerId, dto, imagesFile));
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PreAuthorize("hasAuthority('HUSTLER')")
//    @PostMapping("/create")
//    public ResponseEntity<OfferDto> createOffer(@PathVariable UUID hustlerId, @Valid @RequestBody OfferDto dto)
//    {
//        return ResponseEntity.ok(offerService.createOffer(hustlerId, dto));
//    }

    @PreAuthorize("hasAuthority('HUSTLER')")
    @GetMapping
    public ResponseEntity<List<OfferDto>> getAllOffersByHustlerId(@PathVariable UUID hustlerId)
    {
        return ResponseEntity.ok(offerService.getAllOffers(hustlerId));
    }

    @PreAuthorize("hasAuthority('HUSTLER')")
    @PatchMapping("/{serviceId}/update")
    public ResponseEntity<OfferDto> updateOffer(@PathVariable UUID hustlerId, @PathVariable Integer serviceId, @Valid @ParameterObject RequestOfferDto dto)
    {
        return ResponseEntity.ok(offerService.update(hustlerId, serviceId, dto));
    }

    @PreAuthorize("hasAuthority('HUSTLER')")
    @DeleteMapping("/{serviceId}/delete")
    public ResponseEntity<Void> deleteOffer(@PathVariable UUID hustlerId, @PathVariable Integer serviceId)
    {
        offerService.deleteOffer(hustlerId, serviceId);
        return ResponseEntity.noContent().build();
    }
}
