package com.test.store.infra.persistence.repository.impl;

import com.test.store.infra.persistence.model.JpaPrice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaPriceRepository extends JpaRepository<JpaPrice, Long> {

    @Query("SELECT p FROM JpaPrice p " +
            "WHERE p.brandId = :brandId " +
            "AND p.productId = :productId " +
            "AND :effectiveDate BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC")
    List<JpaPrice> findEffectivePrices(@Param("brandId") Long brandId,
                                       @Param("productId") Long productId,
                                       @Param("effectiveDate") LocalDateTime effectiveDate,
                                       Pageable pageable);
}
