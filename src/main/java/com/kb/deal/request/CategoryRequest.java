package com.kb.deal.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ravi Gupta
 */
@Getter
@Setter
public class CategoryRequest {
    private Long id;
    private String categoryIcon;
    private String categoryName;
    private Integer categoryStage;
    @JsonProperty
    private Boolean isNavigation;
    private Long parentId;
}
