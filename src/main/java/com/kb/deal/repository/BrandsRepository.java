package com.kb.deal.repository;

import com.kb.deal.model.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/**
 * @author Ravi Gupta
 */
public interface BrandsRepository extends JpaRepository<Brands, Long> {

    @Query(value = "select tb.name from Brands tb where tb.trending = ?1 ")
    List<String> getTrendingBrands(Boolean trending);

    @Query(value = "select tb.name from Brands tb where tb.trending = ?1 ")
    List<String> getTrendingProducts(Boolean trending);
}
