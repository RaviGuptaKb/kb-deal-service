package com.kb.deal.service.impl;

import com.kb.deal.model.ProductBanner;
import com.kb.deal.repository.ProductBannerRepository;
import com.kb.deal.request.ProductBannerRequest;
import com.kb.deal.service.BannerService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ravi Gupta
 */
@Service
@Slf4j
public class BannerServiceImpl implements BannerService {

    @Autowired
    ProductBannerRepository productBannerRepository;

    @Override
    public ProductBanner getProductBanner(Long id) {
        Optional<ProductBanner> optionalProductBanner = productBannerRepository.findById(id);
        return optionalProductBanner.orElse(null);
    }

    @Override
    public List<ProductBanner> getProductBannerList() {
        List<ProductBanner> bannerList = productBannerRepository.findAll();
        return bannerList;
    }

    @Override
    public ProductBanner addProductBanner(ProductBannerRequest bannerRequest) {
        ProductBanner banner = new ProductBanner(bannerRequest.getProductCategory(), bannerRequest.getDiscountDetail(), bannerRequest.getImageGalleryId(), bannerRequest.getMessage());
        banner = productBannerRepository.save(banner);
        log.info("product banner saved :: " + banner);
        return banner;
    }

    @Override
    public ProductBanner updateProductBanner(Long id, String productCategory, String discountDetail, Long imageGalleryId, String message) {
        // get product by id
        Optional<ProductBanner> optionalProductBanner = productBannerRepository.findById(id);
        ProductBanner productBanner = optionalProductBanner.orElse(null);
        if (productBanner != null) {
            if (StringUtils.isNotBlank(productCategory))
                productBanner.setProductCategory(productCategory);
            if (StringUtils.isNotBlank(discountDetail))
                productBanner.setDiscountDetail(discountDetail);
            if (imageGalleryId != null)
                productBanner.setImageGalleryId(imageGalleryId);
            if (StringUtils.isNotBlank(message))
                productBanner.setMessage(message);
            // update product
            productBanner = productBannerRepository.save(productBanner);
            log.info("product banner updated :: " + productBanner);
        }
        return productBanner;
    }

    @Override
    public void deleteProductBanner(Long id) throws EmptyResultDataAccessException {
        if (id != null) {
            productBannerRepository.deleteById(id);
            log.info("product banner having id: {} deleted", id);
        } else {
            productBannerRepository.deleteAll();
            log.info("All product banners deleted");
        }
    }
}
