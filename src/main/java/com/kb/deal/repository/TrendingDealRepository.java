package com.kb.deal.repository;

import com.kb.deal.model.TrendingDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/**
 * @author Ravi Gupta
 */
public interface TrendingDealRepository extends JpaRepository<TrendingDeal, Long> {

    @Query(value = "select td from TrendingDeal td where td.parentId  = ?1  AND td.categoryId= ?2 AND td.brandModelId >= ?3 ")
    List<TrendingDeal> getTrendingDeal(Long parentId, Long categoryId, Long brandModelId);

    @Query(value = "select td from TrendingDeal td where td.parentId  = ?1  AND td.categoryId= ?2 ")
    List<TrendingDeal> getTrendingDealByParentIdCategoryId(Long parentId, Long categoryId);

    @Query(value = "select td from TrendingDeal td where td.parentId  = ?1  AND td.brandModelId= ?2 ")
    List<TrendingDeal> getTrendingDealByParentIdBrandModelId(Long parentId, Long brandModelId);

    @Query(value = "select td from TrendingDeal td where td.parentId  = ?1")
    List<TrendingDeal> getTrendingDealByParentId(Long parentId);

    @Query(value = "select td from TrendingDeal td where td.categoryId= ?1 AND td.brandModelId >= ?2 AND td.parentId is null ")
    List<TrendingDeal> getTrendingDealByCategoryIdBrandModelId(Long categoryId, Long brandModelId);

    @Query(value = "select td from TrendingDeal td where td.categoryId= ?1 AND td.parentId is null ")
    List<TrendingDeal> getTrendingDealByCategoryId(Long categoryId);

    @Query(value = "select td from TrendingDeal td where td.brandModelId >= ?2 AND td.parentId is null ")
    List<TrendingDeal> getTrendingDealByBrandModelId(Long brandModelId);

    @Modifying
    @Query("delete from TrendingDeal td where td.categoryId=:categoryId and td.id=:id")
    Integer deleteByCategoryIdTrendingDealId(Long categoryId, Long id);

    @Modifying
    @Query("delete from TrendingDeal td where td.categoryId=:categoryId")
    Integer deleteByCategoryId(Long categoryId);
}
