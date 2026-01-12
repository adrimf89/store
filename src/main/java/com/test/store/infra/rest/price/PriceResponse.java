package com.test.store.infra.rest.price;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponse(
    Long productId,
    Long brandId,
    Long tariffId,
    LocalDateTime startDate,
    LocalDateTime endDate,
    BigDecimal price,
    String currency) {
}
