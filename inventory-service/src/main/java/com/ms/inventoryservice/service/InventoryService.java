package com.ms.inventoryservice.service;

import com.ms.inventoryservice.dto.InventoryUpdatedResponse;

public interface InventoryService {

    boolean isStockAvailable(String skuCode,Integer quantity);

    InventoryUpdatedResponse updateStock(String skuCode, Integer quantity);

}
