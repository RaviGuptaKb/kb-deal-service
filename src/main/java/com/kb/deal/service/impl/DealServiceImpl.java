package com.kb.deal.service.impl;

import com.kb.deal.model.ProductDeal;
import com.kb.deal.repository.ProductDealRepository;
import com.kb.deal.request.DealRequest;
import com.kb.deal.service.DealService;
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
public class DealServiceImpl implements DealService {

    @Autowired
    private ProductDealRepository dealRepository;

    @Override
    public ProductDeal getDeal(Long id) {
        Optional<ProductDeal> optionalProductDeal = dealRepository.findById(id);
        return optionalProductDeal.orElse(null);
    }

    @Override
    public List<ProductDeal> getDeals() {
        List<ProductDeal> productDeals = dealRepository.findAll();
        return productDeals;
    }

    @Override
    public ProductDeal addDeal(DealRequest dealRequest) {
        Timestamp startDate = Timestamp.valueOf(dealRequest.getStartDate());
        Timestamp endDate = Timestamp.valueOf(dealRequest.getEndDate());

        ProductDeal productDeal = new ProductDeal(dealRequest.getName(), dealRequest.getTitle(), dealRequest.getDescription(),
                dealRequest.getBannerId(), startDate, endDate, dealRequest.getProductCategory(), dealRequest.getImageGalleryId(),
                dealRequest.getDiscountRange(), dealRequest.getBrandModelId(), dealRequest.getIsActive());
        productDeal = dealRepository.save(productDeal);
        log.info("product deal saved :: " + productDeal);
        return productDeal;
    }

    @Override
    public ProductDeal updateDeal(Long id, DealRequest dealRequest) {
        Optional<ProductDeal> optionalProductDeal = dealRepository.findById(id);
        ProductDeal productDeal = optionalProductDeal.orElse(null);
        if (productDeal != null) {
            if (StringUtils.isNotBlank(dealRequest.getName()))
                productDeal.setName(dealRequest.getName());
            if (StringUtils.isNotBlank(dealRequest.getTitle()))
                productDeal.setTitle(dealRequest.getTitle());
            if (StringUtils.isNotBlank(dealRequest.getDescription()))
                productDeal.setDescription(dealRequest.getDescription());
            if (dealRequest.getBannerId() != null)
                productDeal.setBannerId(dealRequest.getBannerId());
            if (dealRequest.getStartDate() != null)
                productDeal.setStartDate(Timestamp.valueOf(dealRequest.getStartDate()));
            if (dealRequest.getEndDate() != null)
                productDeal.setEndDate(Timestamp.valueOf(dealRequest.getEndDate()));
            if (StringUtils.isNotBlank(dealRequest.getProductCategory()))
                productDeal.setProductCategory(dealRequest.getProductCategory());
            if (dealRequest.getImageGalleryId() != null)
                productDeal.setImageGalleryId(dealRequest.getImageGalleryId());
            if (dealRequest.getDiscountRange() != null)
                productDeal.setDiscountRange(dealRequest.getDiscountRange());
            if (dealRequest.getBrandModelId() != null)
                productDeal.setBrandModelId(dealRequest.getBrandModelId());
            if (dealRequest.getIsActive() != null)
                productDeal.setIsActive(dealRequest.getIsActive());

            // update deal
            productDeal = dealRepository.save(productDeal);
            log.info("product deal updated :: " + productDeal);
        }
        return productDeal;
    }

    @Override
    public void deleteDeal(Long id) {
        if (id != null) {
            dealRepository.deleteById(id);
            log.info("product deal having id: {} deleted", id);
        } else {
            dealRepository.deleteAll();
            log.info("All product deals deleted");
        }
    }
}
