package com.kb.deal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
/**
 * @author Ravi Gupta
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "banner_length")
    private Integer bannerLength;

    @Column(name = "occasion")
    private String occasion;

    @Column(name = "bank_offer")
    private String bankOffer;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    public Offer() {
    }

    public Offer(Integer bannerLength, String occasion, String bankOffer, Timestamp startDate, Timestamp endDate) {
        this.bannerLength = bannerLength;
        this.occasion = occasion;
        this.bankOffer = bankOffer;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
