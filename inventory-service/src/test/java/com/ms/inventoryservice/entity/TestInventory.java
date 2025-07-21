package com.ms.inventoryservice.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestInventory {

    private static Inventory inventory;

    private static final Long INVENTORY_ID = 1L;
    private static final String INVENTORY_SKUCODE = "Inventory";
    private static final Integer INVENTORY_QUANTITY = 100;

    @BeforeEach
    public void setUp(){
        inventory = new Inventory(INVENTORY_ID,INVENTORY_SKUCODE,INVENTORY_QUANTITY);
    }

    @Test
    public void testInventory(){
        Assertions.assertEquals(INVENTORY_ID, inventory.getId());
        Assertions.assertEquals(INVENTORY_SKUCODE,inventory.getSkuCode());
        Assertions.assertEquals(INVENTORY_QUANTITY,inventory.getQuantity());
    }
}
