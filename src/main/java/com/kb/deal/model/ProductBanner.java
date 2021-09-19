package com.kb.deal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Ravi Gupta
 */
@Entity
@Getter
@Setter
@Table(name = "product_banner")
public class ProductBanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "discount_detail")
    private String discountDetail;

    @Column(name = "image_gallery_id")
    private Long imageGalleryId;

    @Column(name = "message")
    private String message;

    public ProductBanner() {
    }

    public ProductBanner(String productCategory, String discountDetail, Long imageGalleryId, String message) {
        this.productCategory = productCategory;
        this.discountDetail = discountDetail;
        this.imageGalleryId = imageGalleryId;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ProductBanner{" +
                "id=" + id +
                ", productCategory='" + productCategory + '\'' +
                ", discountDetail='" + discountDetail + '\'' +
                ", imageGalleryId=" + imageGalleryId +
                ", message='" + message + '\'' +
                '}';
    }
}
