package com.kb.deal.repository;

import com.kb.deal.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Ravi Gupta
 */
public interface OfferRepository extends JpaRepository<Offer, Long> {
}
