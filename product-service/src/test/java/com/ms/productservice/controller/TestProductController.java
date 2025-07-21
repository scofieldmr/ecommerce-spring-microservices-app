package com.ms.productservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.productservice.dto.ProductDto;
import com.ms.productservice.dto.ProductSaveDto;
import com.ms.productservice.entity.Products;
import com.ms.productservice.mapper.ProductMapper;
import com.ms.productservice.repo.ProductRepository;
import com.ms.productservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(controllers = ProductController.class)
public class TestProductController {

    @MockitoBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testCreateProductDetails() throws Exception {
        ProductSaveDto productsSaveDto = new ProductSaveDto("Product3","Description3",new BigDecimal(3333));

        ProductDto responseDto = new ProductDto(3,"Product3","Description3",new BigDecimal(3333));

        Mockito.when(productService.createProduct(productsSaveDto)).thenReturn(responseDto);

        //when/then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products/add")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(objectMapper.writeValueAsString(productsSaveDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("Product3"))
                .andExpect(jsonPath("$.description").value("Description3"))
                .andExpect(jsonPath("$.price").value(3333));

    }

    @Test
    public void testGetAllProducts() throws Exception {
        ProductDto products1 = new ProductDto(1,"Product1","Description1",new BigDecimal(1111));
        ProductDto products2 = new ProductDto(2,"Product2","Description2",new BigDecimal(2222));

        List<ProductDto> productsDtoList = Arrays.asList(products1,products2);

        Mockito.when(productService.getAllProducts()).thenReturn(productsDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/all")
                                           .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Product1"))
                .andExpect(jsonPath("$[0].description").value("Description1"))
                .andExpect(jsonPath("$[0].price").value(1111));

    }

    @Test
    public void testGetProductById() throws Exception {
        ProductDto productsDto = new ProductDto(1,"Product1","Description1",new BigDecimal(1111));

        Mockito.when(productService.getProductById(1)).thenReturn(productsDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Product1"))
                .andExpect(jsonPath("$.description").value("Description1"))
                .andExpect(jsonPath("$.price").value(1111));
    }
}
