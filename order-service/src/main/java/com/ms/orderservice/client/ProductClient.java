package com.ms.orderservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface ProductClient {

    Logger log = LoggerFactory.getLogger(ProductClient.class);

    @CircuitBreaker(name = "product", fallbackMethod = "fallbackMethod")
    @Retry(name = "product")
    @GetExchange("/api/v1/products/getProdByName/{product_name}")
    ProductResponseDto getProductDetailsByName(@PathVariable("product_name") String product_name);

    default boolean fallbackMethod(String code, Throwable throwable) {
        log.info("Cannot get Product details {}, failure reason: {}", code, throwable.getMessage());
        return false;
    }
}
