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

    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod1")
    @Retry(name = "inventory")
    @GetExchange("/api/v1/inventory/stockAvailable/{sku_code}/{quantity}")
    boolean isStockAvailable(@PathVariable("sku_code") String sku_code,
                             @PathVariable("quantity") Integer quantity);
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod2")
    @Retry(name = "inventory")
    @PostExchange("/api/v1/inventory/updateInventoryStock/{sku_code}/{quantity}")
    InventoryUpdatedResponse updateInventoryStock(@PathVariable("sku_code") String sku_code,
                             @PathVariable("quantity") Integer quantity);


    default boolean fallbackMethod1(String sku_Code, Integer quantity, Throwable throwable) {
        log.info("Cannot get inventory for skucode {}, failure reason: {}", sku_Code, throwable.getMessage());
        return false;

    }
        default boolean fallbackMethod2(String sku_Code, Integer quantity, Throwable throwable) {
        log.info("Cannot get inventory for skucode {}, failure reason: {}", sku_Code, throwable.getMessage());
        return false;
  }
}
