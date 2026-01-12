package com.test.store.infra.rest.price;

import com.test.store.application.price.PriceService;
import com.test.store.domain.model.Price;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
@Tag(name = "Prices", description = "Store price management")
public class PriceController {

    private final PriceService priceService;

    @GetMapping("/{brandId}/products/{productId}")
    @Operation(summary = "Get effective price for a product by brand and date")
    public ResponseEntity<PriceResponse> getEffectivePrice(@PathVariable Long brandId,
                                                  @PathVariable Long productId,
                                                  @RequestParam("effectiveDate") LocalDateTime effectiveDate) {
        log.info("Received request to get price for productId: {}, brandId: {}, effectiveDate: {}", productId, brandId, effectiveDate);
        return ResponseEntity.ok(toPriceResponse(priceService.getEffectivePrice(productId, brandId, effectiveDate)));
    }

    private PriceResponse toPriceResponse(Price price) {
        return new PriceResponse(
                price.getProductId(),
                price.getBrandId(),
                price.getId(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice(),
                price.getCurrency()
        );
    }
}
