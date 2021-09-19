package com.kb.deal.service.impl;

import com.kb.deal.model.Category;
import com.kb.deal.repository.CategoryRepository;
import com.kb.deal.request.CategoryRequest;
import com.kb.deal.service.CategoryService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ravi Gupta
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getcategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    @Override
    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @Override
    public Category addCategory(CategoryRequest categoryRequest) {
        Category category = new Category(categoryRequest.getId(), categoryRequest.getCategoryIcon(), categoryRequest.getCategoryName(), categoryRequest.getCategoryStage(), categoryRequest.getIsNavigation(), categoryRequest.getParentId());
        category = categoryRepository.save(category);
        log.info("product category saved :: " + category);
        return category;
    }

    @Override
    public Category updateCategory(Long id, CategoryRequest categoryRequest) {
        // get product category by id
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category = optionalCategory.orElse(null);
        if (category != null) {
            if (categoryRequest.getId() != null)
                category.setId(categoryRequest.getId());
            if (StringUtils.isNotBlank(categoryRequest.getCategoryIcon()))
                category.setCategoryIcon(categoryRequest.getCategoryIcon());
            if (StringUtils.isNotBlank(categoryRequest.getCategoryName()))
                category.setCategoryName(categoryRequest.getCategoryName());
            if (categoryRequest.getCategoryStage() != null)
                category.setCategoryStage(categoryRequest.getCategoryStage());
            if (categoryRequest.getIsNavigation() != null)
                category.setIsNavigation(categoryRequest.getIsNavigation());
            if (categoryRequest.getParentId() != null)
                category.setParentId(categoryRequest.getParentId());
            // update category
            category = categoryRepository.save(category);
            log.info("product category updated :: " + category);
        }
        return category;
    }

    @Override
    public void deleteCategory(Long id) {
        if (id != null) {
            categoryRepository.deleteById(id);
            log.info("product category having id: {} deleted", id);
        } else {
            categoryRepository.deleteAll();
            log.info("All product categories deleted");
        }
    }
}
