package com.ms.productservice.entity;


import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

public class TestProducts {

    private static Products products;

    private static final long id = 1;
    private static final String name = "Product 1";
    private static final String description = "Description 1";
    private static final BigDecimal price = new BigDecimal(1234);

    @BeforeEach
    public void setUpBeforeClass() {
        products = new Products();
        products.setId(id);
        products.setName("Product 1");
        products.setDescription("Description 1");
        products.setPrice(new BigDecimal(1234));
    }

    @Test
    public void testProduct() {
        Assertions.assertEquals(name, products.getName());
        Assertions.assertEquals(description, products.getDescription());
        Assertions.assertEquals(price, products.getPrice());
    }
}
