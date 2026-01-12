package com.test.store.infra.config;

import com.test.store.application.price.PriceService;
import com.test.store.domain.repository.PriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public PriceService priceService(PriceRepository priceRepository) {
        return new PriceService(priceRepository);
    }
}
