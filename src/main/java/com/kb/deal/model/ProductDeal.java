package com.kb.deal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Ravi Gupta
 */
@Entity
@Getter
@Setter
@Table(name = "product_deal")
public class ProductDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "banner_id")
    private Long bannerId;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "image_gallery_id")
    private Long imageGalleryId;

    @Column(name = "discount_range")
    private Integer discountRange;

    @Column(name = "brand_model_id")
    private Long brandModelId;

    @Column(name = "is_active")
    private Boolean isActive;

    public ProductDeal() {

    }

    public ProductDeal(String name, String title, String description, Long bannerId, Timestamp startDate, Timestamp endDate, String productCategory, Long imageGalleryId, Integer discountRange, Long brandModelId, Boolean isActive) {
        this.name = name;
        this.title = title;
        this.description = description;
        this.bannerId = bannerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.productCategory = productCategory;
        this.imageGalleryId = imageGalleryId;
        this.discountRange = discountRange;
        this.brandModelId = brandModelId;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "ProductDeal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", bannerId=" + bannerId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", productCategory='" + productCategory + '\'' +
                ", imageGalleryId=" + imageGalleryId +
                ", discountRange=" + discountRange +
                ", brandModelId=" + brandModelId +
                ", isActive=" + isActive +
                '}';
    }
}
