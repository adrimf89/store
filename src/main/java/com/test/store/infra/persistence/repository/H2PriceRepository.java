package com.test.store.infra.persistence.repository;

import com.test.store.domain.model.Price;
import com.test.store.domain.repository.PriceRepository;
import com.test.store.infra.persistence.model.JpaPrice;
import com.test.store.infra.persistence.repository.impl.JpaPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class H2PriceRepository implements PriceRepository {

    private final JpaPriceRepository jpaPriceRepository;

    @Override
    public Optional<Price> findEffectivePrice(Long productId, Long brandId, LocalDateTime effectiveDate) {
        return jpaPriceRepository.findEffectivePrices(brandId, productId, effectiveDate, PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .map(this::mapToDomain);
    }

    private Price mapToDomain(JpaPrice jpaPrice) {
        return new Price(
                jpaPrice.getId(),
                jpaPrice.getBrandId(),
                jpaPrice.getProductId(),
                jpaPrice.getStartDate(),
                jpaPrice.getEndDate(),
                jpaPrice.getPriority(),
                jpaPrice.getPrice(),
                jpaPrice.getCurrency()
        );
    }
}
