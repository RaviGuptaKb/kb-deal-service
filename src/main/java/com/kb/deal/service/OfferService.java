package com.kb.deal.service;

import com.kb.deal.model.Offer;
import com.kb.deal.request.OfferRequest;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Ravi Gupta
 */
public interface OfferService {

    Offer getOffer(Long id);

    List<Offer> getOfferList();

    Offer addOffer(OfferRequest offerRequest);

    Offer updateOffer(Long id, Integer bannerLength, String occasion, String bankOffer, String startDate, String endDate);

    void deleteOffer(Long id);
}
