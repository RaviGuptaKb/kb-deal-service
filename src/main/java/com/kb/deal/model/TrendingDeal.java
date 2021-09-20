package com.kb.deal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
/**
 * @author Ravi Gupta
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "trending_deal")
public class TrendingDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "discount_range")
    private Integer discountRange;

    @Column(name = "image_gallery_id")
    private Long imageGalleryId;

    @Column(name = "description")
    private String description;

    @Column(name = "brand_model_id")
    private Long brandModelId;

    public TrendingDeal() {

    }

    public TrendingDeal(Long categoryId, Long parentId, Integer discountRange, Long imageGalleryId, String description, Long brandModelId) {
        this.categoryId = categoryId;
        this.parentId = parentId;
        this.discountRange = discountRange;
        this.imageGalleryId = imageGalleryId;
        this.description = description;
        this.brandModelId = brandModelId;
    }
}
