package com.kb.deal.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ravi Gupta
 */
@Getter
@Setter
public class OfferRequest {
    private Integer bannerLength;
    private String occasion;
    private String bankOffer;
    private String startDate;
    private String endDate;
}
