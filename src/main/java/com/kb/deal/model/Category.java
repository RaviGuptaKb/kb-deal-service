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
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "category_icon")
    private String categoryIcon;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_stage")
    private Integer categoryStage;

    @Column(name = "is_navigation")
    private Boolean isNavigation;

    @Column(name = "parent_id")
    private Long parentId;

    public Category() {

    }

    public Category(Long id, String categoryIcon, String categoryName, Integer categoryStage, Boolean isNavigation, Long parentId) {
        this.id = id;
        this.categoryIcon = categoryIcon;
        this.categoryName = categoryName;
        this.categoryStage = categoryStage;
        this.isNavigation = isNavigation;
        this.parentId = parentId;
    }
}
