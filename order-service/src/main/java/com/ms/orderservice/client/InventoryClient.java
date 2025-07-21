package com.ms.orderservice.client;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface InventoryClient {

    static Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/v1/inventory/stockAvailable/{sku_code}/{quantity}")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    boolean isStockAvailable(@PathVariable("sku_code") String sku_code,
                             @PathVariable("quantity") int quantity);

    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    @PostExchange("/api/v1/inventory/updateInventoryStock/{sku_code}/{quantity}")
    InventoryUpdatedResponse updateInventoryStock(@PathVariable("sku_code") String sku_code,
                             @PathVariable("quantity") int quantity);

    default boolean fallbackMethod(String code, Integer quantity, Throwable throwable) {
        log.info("Cannot get inventory for skucode {}, failure reason: {}", code, throwable.getMessage());
        return false;
    }
}
