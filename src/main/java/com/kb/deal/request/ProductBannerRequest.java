package com.kb.deal.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ravi Gupta
 */
@Getter
@Setter
public class ProductBannerRequest {
    private String productCategory;
    private String discountDetail;
    private Long imageGalleryId;
    private String message;
}
