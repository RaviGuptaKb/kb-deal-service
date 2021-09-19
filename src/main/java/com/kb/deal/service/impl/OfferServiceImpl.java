package com.kb.deal.service.impl;

import com.kb.deal.model.Offer;
import com.kb.deal.repository.OfferRepository;
import com.kb.deal.request.OfferRequest;
import com.kb.deal.service.OfferService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * @author Ravi Gupta
 */
@Service
@Slf4j
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public Offer getOffer(Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        return offer.orElse(null);
    }

    @Override
    public List<Offer> getOfferList() {
        List<Offer> offerList = offerRepository.findAll();
        return offerList;
    }

    @Override
    public Offer addOffer(OfferRequest offerRequest) {
        Timestamp startDate = Timestamp.valueOf(offerRequest.getStartDate());
        Timestamp endDate = Timestamp.valueOf(offerRequest.getEndDate());
        Offer offer = new Offer(offerRequest.getBannerLength(), offerRequest.getOccasion(), offerRequest.getBankOffer(), startDate, endDate);
        offer = offerRepository.save(offer);
        log.info("product offer saved :: " + offer);
        return offer;
    }

    @Override
    public Offer updateOffer(Long id, Integer bannerLength, String occasion, String bankOffer, String startDate, String endDate) {
        // get product by id
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        Offer offer = optionalOffer.orElse(null);
        if (offer != null) {
            if (bannerLength != null)
                offer.setBannerLength(bannerLength);
            if (StringUtils.isNotBlank(occasion))
                offer.setOccasion(occasion);
            if (StringUtils.isNotBlank(bankOffer))
                offer.setBankOffer(bankOffer);
            if (startDate != null)
                offer.setStartDate(Timestamp.valueOf(startDate));
            if (endDate != null)
                offer.setEndDate(Timestamp.valueOf(endDate));
            // update offer
            offer = offerRepository.save(offer);
            log.info("product offer updated :: " + offer);
        }
        return offer;
    }

    @Override
    public void deleteOffer(Long id) {
        if (id != null) {
            offerRepository.deleteById(id);
            log.info("product offer having id: {} deleted", id);
        } else {
            offerRepository.deleteAll();
            log.info("All product offers deleted");
        }
    }
}
