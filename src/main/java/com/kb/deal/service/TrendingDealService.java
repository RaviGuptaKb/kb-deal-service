package com.kb.deal.service;


import com.kb.deal.model.TrendingDeal;
import com.kb.deal.request.TrendingDealRequest;

import java.util.List;

/**
 * @author Ravi Gupta
 */
public interface TrendingDealService {

    List<TrendingDeal> getTrendingDeal(Long parentId, Long categoryId, Long brandModelId);

    List<String> getTrendingBrands();

    List<String> getTrendingProducts(Long parentId, Long categoryId);

    TrendingDeal addTrendingDeal(TrendingDealRequest trendingRequest);

    TrendingDeal updateTrendingDeal(Long id, TrendingDealRequest trendingRequest);

    Integer deleteTrendingDeal(Long categoryId, Long id);
}
