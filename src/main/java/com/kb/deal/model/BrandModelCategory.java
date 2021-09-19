package com.kb.deal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "brand_model_category")
public class BrandModelCategory {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "name")
    private String name;

    @Column(name = "brand_model_id")
    private Long brandModelId;

    @Column(name = "category_id")
    private Long categoryId;
}
