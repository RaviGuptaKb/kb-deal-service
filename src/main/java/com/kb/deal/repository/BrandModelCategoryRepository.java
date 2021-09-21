package com.kb.deal.repository;

import com.kb.deal.model.BrandModelCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Ravi Gupta
 */
public interface BrandModelCategoryRepository extends JpaRepository<BrandModelCategory, Long> {

    @Query(value = "select bmc.id from BrandModelCategory bmc where bmc.categoryId = ?1 ")
    List<Long> findByCategoryId(Long categoryId);
}
