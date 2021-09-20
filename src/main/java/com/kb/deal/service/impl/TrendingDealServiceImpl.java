package com.kb.deal.service.impl;

import com.kb.deal.model.TrendingDeal;
import com.kb.deal.repository.BrandsRepository;
import com.kb.deal.repository.TrendingDealRepository;
import com.kb.deal.request.TrendingDealRequest;
import com.kb.deal.service.TrendingDealService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Ravi Gupta
 */
@Service
@Slf4j
public class TrendingDealServiceImpl implements TrendingDealService {

    @Autowired
    private TrendingDealRepository trendingDealRepository;

    @Autowired
    private BrandsRepository brandsRepository;

    @Override
    public List<TrendingDeal> getTrendingDeal(Long parentId, Long categoryId, Long brandModelId) {
        List<TrendingDeal> trendingDeals = new ArrayList<>();
        if (parentId != null) {
            if (categoryId != null && brandModelId != null) {
                trendingDeals = trendingDealRepository.getTrendingDeal(parentId, categoryId, brandModelId);
            } else if (categoryId != null && brandModelId == null) {
                trendingDeals = trendingDealRepository.getTrendingDealByParentIdCategoryId(parentId, categoryId);
            } else if (categoryId == null && brandModelId != null) {
                trendingDeals = trendingDealRepository.getTrendingDealByParentIdBrandModelId(parentId, brandModelId);
            } else {
                trendingDeals = trendingDealRepository.getTrendingDealByParentId(parentId);
            }
        } else {
            if (categoryId != null && brandModelId != null) {
                trendingDeals = trendingDealRepository.getTrendingDealByCategoryIdBrandModelId(categoryId, brandModelId);
            } else if (categoryId != null && brandModelId == null) {
                trendingDeals = trendingDealRepository.getTrendingDealByCategoryId(categoryId);
            } else if (categoryId == null && brandModelId != null) {
                trendingDeals = trendingDealRepository.getTrendingDealByBrandModelId(brandModelId);
            } else if (categoryId == null && brandModelId == null)
                trendingDeals = trendingDealRepository.findAll();
        }
        return trendingDeals;
    }

    @Override
    public List<String> getTrendingBrands() {
        Boolean trending = true;
        List<String> brands = brandsRepository.getTrendingBrands(trending);
        return brands;
    }

    @Override
    public List<String> getTrendingProducts(Long parentId, Long categoryId) {
        List<String> trendingProducts = new ArrayList<>();
        //TODO: need to implement
//        customer_product_review.product_id in (select p from product p where p.brand_model_category in
//        (select c.id from category c where c.parent_id = null) and
//        brand_model_category.category_id in (select c.id from category c where c.parent_id != null and c.id = categoryId)
//        and customer_product_review.avg_rating > 4
        return trendingProducts;
    }

    @Override
    public TrendingDeal addTrendingDeal(TrendingDealRequest trendingRequest) {
        TrendingDeal deal = new TrendingDeal(trendingRequest.getCategoryId(), trendingRequest.getParentId(), trendingRequest.getDiscountRange(),
                trendingRequest.getImageGalleryId(), trendingRequest.getDescription(), trendingRequest.getBrandModelId());
        deal = trendingDealRepository.save(deal);
        log.info("trending deal saved :: " + deal);
        return deal;
    }

    @Override
    public TrendingDeal updateTrendingDeal(Long id, TrendingDealRequest trendingRequest) {
        // get trending deal by id
        Optional<TrendingDeal> optionalTrendingDeal = trendingDealRepository.findById(id);
        TrendingDeal trendingDeal = optionalTrendingDeal.orElse(null);
        if (trendingDeal != null) {
            if (trendingRequest.getCategoryId() != null)
                trendingDeal.setCategoryId(trendingRequest.getCategoryId());
            if (trendingRequest.getParentId() != null)
                trendingDeal.setParentId(trendingRequest.getParentId());
            if (trendingRequest.getDiscountRange() != null)
                trendingDeal.setDiscountRange(trendingRequest.getDiscountRange());
            if (trendingRequest.getImageGalleryId() != null)
                trendingDeal.setImageGalleryId(trendingRequest.getImageGalleryId());
            if (StringUtils.isNotBlank(trendingRequest.getDescription()))
                trendingDeal.setDescription(trendingRequest.getDescription());
            if (trendingRequest.getBrandModelId() != null)
                trendingDeal.setBrandModelId(trendingRequest.getBrandModelId());
            // update a trending deal
            trendingDeal = trendingDealRepository.save(trendingDeal);
            log.info("trending deal updated :: " + trendingDeal);
        }
        return trendingDeal;
    }

    @Override
    @Transactional
    public Integer deleteTrendingDeal(Long categoryId, Long id) {
        Integer recordsDeleted = 0;
        if (id != null) {
            recordsDeleted = trendingDealRepository.deleteByCategoryIdTrendingDealId(categoryId, id);
            log.info("Deleted rows for trending deal having id: {} categoryId {} are : {}", id, categoryId, recordsDeleted);
        } else {
            recordsDeleted = trendingDealRepository.deleteByCategoryId(categoryId);
            log.info("Deleted rows for all trending deals for given category are: {}", recordsDeleted);
        }
        return recordsDeleted;
    }
}
