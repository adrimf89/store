package com.test.store.infra.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "prices")
public class JpaPrice {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private Integer priority;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String currency;
}
