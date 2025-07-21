package com.ms.inventoryservice.service;

import com.ms.inventoryservice.dto.InventoryUpdatedResponse;
import com.ms.inventoryservice.entity.Inventory;
import com.ms.inventoryservice.exception.InventoryNotFoundException;
import com.ms.inventoryservice.repo.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImp implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImp(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    @Override
    public boolean isStockAvailable(String skuCode, Integer quantity) {

        boolean stockAvailable = inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode,quantity);

        return stockAvailable;
    }

    @Override
    public InventoryUpdatedResponse updateStock(String skuCode, Integer quantity) {

        Inventory inventoryStock = inventoryRepository.findInventoryBySkuCode(skuCode)
                .orElseThrow(() -> new InventoryNotFoundException("Product Not Found in the Inventory :" + skuCode));

        if(quantity < inventoryStock.getQuantity()){
            int newQuantity = inventoryStock.getQuantity() - quantity;
            inventoryStock.setQuantity(newQuantity);
            Inventory updatedStock = inventoryRepository.save(inventoryStock);
            return new InventoryUpdatedResponse(updatedStock.getSkuCode(),updatedStock.getQuantity(),
                     "Stock Updated Successfully");
        }
        else{
            throw new InventoryNotFoundException("Product Stock is Not Available :" + skuCode);
        }

    }


}
