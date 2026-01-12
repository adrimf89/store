package com.test.store.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Price {

    private Long id;
    private Long brandId;
    private Long productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;
    private BigDecimal price;
    private String currency;

    public Price(Long id, Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate, Integer priority, BigDecimal price, String currency) {
        this.id = id;
        this.brandId = brandId;
        this.productId = productId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Long getProductId() {
        return productId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}
