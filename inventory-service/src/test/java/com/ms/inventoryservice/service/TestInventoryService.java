package com.ms.inventoryservice.service;

import com.ms.inventoryservice.entity.Inventory;
import com.ms.inventoryservice.repo.InventoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestInventoryService {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryServiceImp inventoryService;

    @Test
    public void testIsStockAvailable() {
        Inventory inventory = new Inventory(1L,"Iphone 6X",100);

        when(inventoryRepository
                .existsBySkuCodeAndQuantityIsGreaterThanEqual(inventory.getSkuCode(), inventory.getQuantity()))
        .thenReturn(true);

        boolean isStockAvailable = inventoryService.isStockAvailable(inventory.getSkuCode(), inventory.getQuantity());

        Assertions.assertTrue(isStockAvailable,"Stock available");
    }

    @Test
    public void testIsStockNotAvailable() {
        Inventory inventory = new Inventory(1L,"Iphone 6X",100);

        when(inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(inventory.getSkuCode(), inventory.getQuantity()))
          .thenReturn(false);

        boolean isStockAvailable = inventoryService.isStockAvailable(inventory.getSkuCode(), inventory.getQuantity());

        Assertions.assertFalse(isStockAvailable,"Stock not available");
    }

}
