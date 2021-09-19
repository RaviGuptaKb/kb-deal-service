package com.kb.deal.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ravi Gupta
 */
@Getter
@Setter
public class DealRequest {
    private String name;
    private String title;
    private String description;
    private Long bannerId;
    private String startDate;
    private String endDate;
    private String productCategory;
    private Long imageGalleryId;
    private Integer discountRange;
    private Long brandModelId;
    @JsonProperty
    private Boolean isActive;
}
