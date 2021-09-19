package com.kb.deal.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ravi Gupta
 */
@Getter
@Setter
public class TrendingDealRequest {
    private Long categoryId;
    private Long parentId;
    private Integer discountRange;
    private Long imageGalleryId;
    private String description;
    private Long brandModelId;
}
