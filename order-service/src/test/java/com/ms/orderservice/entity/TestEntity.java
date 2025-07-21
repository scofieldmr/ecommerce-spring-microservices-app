package com.ms.orderservice.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TestEntity {

    private static Orders orders;

    private static final long OrderId = 1L;
    private static final String OrderNumber = "OrderNumber";
    private static final String OrderName = "Order";
    private static final BigDecimal OrderPrice = new BigDecimal(1.0);
    private static final Integer OrderQuantity = 1;

    @BeforeEach
    void setUp() {
        orders = new Orders(OrderId,OrderNumber,OrderName,OrderPrice,OrderQuantity);
    }

    @Test
    void testOrders(){
        Assertions.assertEquals(OrderId,orders.getId());
        Assertions.assertEquals(OrderNumber,orders.getOrderNumber());
        Assertions.assertEquals(OrderName,orders.getSkuCode());
        Assertions.assertEquals(OrderPrice,orders.getPrice());
    }
}
