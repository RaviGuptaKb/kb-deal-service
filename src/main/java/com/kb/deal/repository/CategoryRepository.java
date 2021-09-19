package com.kb.deal.repository;

import com.kb.deal.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Ravi Gupta
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
