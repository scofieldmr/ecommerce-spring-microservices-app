package com.ms.orderservice.client;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface InventoryClient {

    @GetExchange("/api/v1/inventory/stockAvailable/{sku_code}/{quantity}")
    boolean isStockAvailable(@PathVariable("sku_code") String sku_code,
                             @PathVariable("quantity") int quantity);

    @PostExchange("/api/v1/inventory/updateInventoryStock/{sku_code}/{quantity}")
    InventoryUpdatedResponse updateInventoryStock(@PathVariable("sku_code") String sku_code,
                             @PathVariable("quantity") int quantity);
}
