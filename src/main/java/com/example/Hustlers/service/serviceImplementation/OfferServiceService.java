package com.example.Hustlers.service.serviceImplementation;

import com.example.Hustlers.mapper.OfferMapper;
import com.example.Hustlers.dto.OfferDto;
import com.example.Hustlers.model.HustlerProfile;
import com.example.Hustlers.model.Offer;
import com.example.Hustlers.repository.HustlerRepository;
import com.example.Hustlers.repository.OfferRepository;
import com.example.Hustlers.service.OfferServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OfferServiceService implements OfferServiceInterface {

    private final OfferRepository offerRepository;
    private final HustlerRepository hustlerRepository;


//de judecat cum fac dto-urile pentru "Offer"  .
    @Override
    public OfferDto createOffer(UUID hustlerId, OfferDto dto) {
        HustlerProfile hustler = hustlerRepository.findById(hustlerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hustler not found"));
        Offer offer = new Offer(dto);
        offer.setHustler(hustler);
        hustler.getServiceCatalog().add(offer);
        Offer offerToSave = offerRepository.save(offer);

        return new OfferDto(offerToSave);
    }


    @Override//aici pot sa iau lista direct din hustler
    public List<OfferDto> getAllOffers(UUID hustlerId) {
        HustlerProfile hustler = hustlerRepository.findById(hustlerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hustler not found"));
        return OfferMapper.toDtoList(hustler.getServiceCatalog().stream().toList());

    }



    @Override
    public void deleteOffer(UUID hustlerId, Integer serviceId)
    {
        if(hustlerRepository.findById(hustlerId).isPresent()) {
            Offer offer = offerRepository.findById(serviceId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));
            offerRepository.delete(offer);
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hustler not found");
//        System.out.println(hustlerRepository.findById(hustlerId).get().getServiceCatalog());
    }

    @Override
    public OfferDto getOffer(UUID hustlerId) {
        return null;
    }


}
