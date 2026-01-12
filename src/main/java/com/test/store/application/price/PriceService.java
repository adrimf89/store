package com.test.store.application.price;

import com.test.store.domain.exception.EntityNotFoundException;
import com.test.store.domain.exception.InvalidInputException;
import com.test.store.domain.model.Price;
import com.test.store.domain.repository.PriceRepository;

import java.time.LocalDateTime;

import static com.test.store.domain.exception.Messages.PRICE_NOT_FOUND;
import static com.test.store.domain.exception.Messages.PRICE_PARAMETERS_NULL;

public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getEffectivePrice(Long productId, Long brandId, LocalDateTime effectiveDate) {
        validateEffectivePriceParameters(productId, brandId, effectiveDate);
        return priceRepository.findEffectivePrice(productId, brandId, effectiveDate)
                .orElseThrow(() -> new EntityNotFoundException(PRICE_NOT_FOUND.formatted(productId, brandId, effectiveDate)));
    }

    private void validateEffectivePriceParameters(Long productId, Long brandId, LocalDateTime effectiveDate) {
        if (productId == null || brandId == null || effectiveDate == null) {
            throw new InvalidInputException(PRICE_PARAMETERS_NULL);
        }
    }
}
