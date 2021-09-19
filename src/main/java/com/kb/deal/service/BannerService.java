package com.kb.deal.service;

import com.kb.deal.model.ProductBanner;
import com.kb.deal.request.ProductBannerRequest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

/**
 * @author Ravi Gupta
 */
public interface BannerService {

    ProductBanner getProductBanner(Long id);

    List<ProductBanner> getProductBannerList();

    ProductBanner addProductBanner(ProductBannerRequest productBannerRequest);

    ProductBanner updateProductBanner(Long id, String productCategory, String discountDetail, Long imageGalleryId, String message);

    void deleteProductBanner(Long id) throws EmptyResultDataAccessException;
}
