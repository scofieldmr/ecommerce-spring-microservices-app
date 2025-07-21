package com.ms.productservice.repository;


import com.ms.productservice.entity.Products;
import com.ms.productservice.repo.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class TestProductRepo {

    private static Products products;

    private static List<Products> productsList;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        products = new Products("Product1","Description1",new BigDecimal(1111));

        productsList = Arrays.asList(
                new Products("Product2","Description2",new BigDecimal(2222)),
                new Products("Product3","Description3",new BigDecimal(3333))
        );
    }

    @Test
    public void testAddProduct() {
        Products savedInDb = productRepository.save(products);
        Optional<Products> productsOptional = productRepository.findById(savedInDb.getId());

        Assertions.assertTrue(productsOptional.isPresent());
        Products getFromDb = productsOptional.get();

        Assertions.assertEquals(savedInDb.getId(),getFromDb.getId());
        Assertions.assertEquals(savedInDb.getName(),getFromDb.getName());
    }

    @Test
    public void testGetAllProducts() {
        List<Products> savedInDb = productRepository.saveAll(productsList);
        List<Products> productsList = productRepository.findAll();
        Assertions.assertEquals(2, productsList.size());
    }

    @Test
    public void testGetProductById() {
        Products savedInDb = productRepository.save(products);
        Optional<Products> productsOptional = productRepository.findById(savedInDb.getId());
        Assertions.assertTrue(productsOptional.isPresent());
        Products getFromDb = productsOptional.get();
        Assertions.assertEquals(savedInDb.getId(),getFromDb.getId());
    }




}
