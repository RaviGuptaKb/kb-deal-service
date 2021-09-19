package com.kb.deal.service;

import com.kb.deal.model.ProductDeal;
import com.kb.deal.request.DealRequest;

import java.util.List;

/**
 * @author Ravi Gupta
 */
public interface DealService {
    ProductDeal getDeal(Long id);

    List<ProductDeal> getDeals();

    ProductDeal addDeal(DealRequest dealRequest);

    ProductDeal updateDeal(Long id, DealRequest dealRequest);

    void deleteDeal(Long id);
}
