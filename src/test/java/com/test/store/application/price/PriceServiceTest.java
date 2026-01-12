package com.test.store.application.price;

import com.test.store.domain.exception.EntityNotFoundException;
import com.test.store.domain.exception.InvalidInputException;
import com.test.store.domain.model.Price;
import com.test.store.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    @Test
    void getEffectivePrice_shouldReturnPrice_whenValueExists() {
        var productId = new Random().nextLong();
        var brandId = new Random().nextLong();
        var effectiveDate = LocalDateTime.now();

        Price expectedPrice = new Price(new Random().nextLong(), brandId, productId,
                effectiveDate.minusDays(1), effectiveDate.plusDays(1),
                1, null, "EUR");
        when(priceRepository.findEffectivePrice(productId, brandId, effectiveDate))
                .thenReturn(Optional.of(expectedPrice));

        Price actualPrice = priceService.getEffectivePrice(productId, brandId, effectiveDate);

        assertNotNull(actualPrice);
        assertEquals(expectedPrice.getId(), actualPrice.getId());
        assertEquals(expectedPrice.getBrandId(), actualPrice.getBrandId());
        assertEquals(expectedPrice.getProductId(), actualPrice.getProductId());
        assertEquals(expectedPrice.getPrice(), actualPrice.getPrice());
    }

    @ParameterizedTest(name = "#{index} - Throws exception for parameters (ProductId = {0}, BrandId = {1} and effectiveDate = {2})")
    @CsvSource({
            ", 1, 2024-01-01T10:00:00",
            "1, , 2024-01-01T10:00:00",
            "1, 1,"
    })
    void getEffectivePrice_shouldThrowException_whenParametersAreNull(Long productId,
                                                                      Long brandId,
                                                                      LocalDateTime effectiveDate) {
        var actualException = assertThrows(InvalidInputException.class, () ->
                priceService.getEffectivePrice(productId, brandId, effectiveDate)
        );
        var expectedMessage = "Product ID, Brand ID, and Effective Date must not be null";
        assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    void getEffectivePrice_shouldThrowException_whenPriceNotFound() {
        var productId = new Random().nextLong();
        var brandId = new Random().nextLong();
        var effectiveDate = LocalDateTime.now();
        when(priceRepository.findEffectivePrice(productId, brandId, effectiveDate))
                .thenReturn(Optional.empty());

        var actualException = assertThrows(EntityNotFoundException.class, () ->
                priceService.getEffectivePrice(productId, brandId, effectiveDate)
        );
        var expectedMessage = "Price not found for Product ID '%d', Brand ID '%d' and effective date '%s'"
                .formatted(productId, brandId, effectiveDate);
        assertEquals(expectedMessage, actualException.getMessage());
    }
}