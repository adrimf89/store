package com.test.store.domain.repository;

import com.test.store.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

    Optional<Price> findEffectivePrice(Long productId, Long brandId, LocalDateTime effectiveDate);
}
