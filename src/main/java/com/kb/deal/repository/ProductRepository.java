package com.kb.deal.repository;

import com.kb.deal.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select p from Product p where p.brandModelCategoryId  in ?1  AND p.onOffer= ?2 ")
    List<Product> getTrendingProducts(List<Long> brandModelCategoryIds, Boolean onOffer);
}
