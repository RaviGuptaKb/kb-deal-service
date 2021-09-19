package com.kb.deal.service;

import com.kb.deal.model.Category;
import com.kb.deal.request.CategoryRequest;

import java.util.List;

/**
 * @author Ravi Gupta
 */
public interface CategoryService {
    Category getcategory(Long id);

    List<Category> getCategories();

    Category addCategory(CategoryRequest categoryRequest);

    Category updateCategory(Long id, CategoryRequest categoryRequest);

    void deleteCategory(Long id);
}
