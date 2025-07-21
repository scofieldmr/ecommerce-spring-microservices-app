package com.ms.productservice.service;

import com.ms.productservice.dto.ProductDto;
import com.ms.productservice.dto.ProductSaveDto;
import com.ms.productservice.entity.Products;
import com.ms.productservice.mapper.ProductMapper;
import com.ms.productservice.repo.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestProductService {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImp productService;

@Test
public void testAddProduct() {
    ProductSaveDto dto = new ProductSaveDto("P1", "Desc", new BigDecimal("123"));

    Products entity = new Products(1L, "P1", "Desc", new BigDecimal("123"));

    ProductDto productDto = new ProductDto(1L, "P1", "Desc", new BigDecimal("123"));

    when(productMapper.productSaveToProduct(dto)).thenReturn(entity);
    when(productRepository.save(entity)).thenReturn(entity);
    when(productMapper.productToProductDto(entity)).thenReturn(productDto);

    ProductDto result = productService.createProduct(dto);

    Assertions.assertEquals("P1", result.getName());
}

    @Test
    public void testGetAllProducts() {

        Products products1 = new Products("P1", "Desc1", new BigDecimal("123"));
        Products products2 = new Products("P2", "Desc2", new BigDecimal("456"));

        List<Products> productsList = Arrays.asList(products1, products2);

        ProductDto dto1 = new ProductDto(1,"P1", "Desc1", new BigDecimal("123"));
        ProductDto dto2 = new ProductDto(2,"P2", "Desc2", new BigDecimal("456"));

        List<ProductDto> productsDtoList = Arrays.asList(dto1, dto2);

        when(productRepository.findAll()).thenReturn(productsList);

        when(productMapper.productToProductDto(products1)).thenReturn(dto1);
        when(productMapper.productToProductDto(products2)).thenReturn(dto2);

        List<ProductDto> result = productService.getAllProducts();

        Assertions.assertEquals(productsDtoList.size(), result.size());
        Assertions.assertEquals(productsDtoList.get(0), result.get(0));
        Assertions.assertEquals(productsDtoList.get(1), result.get(1));

    }

    @Test
    public void testGetProductById() {
        Products products1 = new Products("P1", "Desc1", new BigDecimal("123"));

        ProductDto productDto = new ProductDto(1L, "P1", "Desc1", new BigDecimal("123"));

        when(productRepository.findById(1L)).thenReturn(Optional.of(products1));
        when(productMapper.productToProductDto(products1)).thenReturn(productDto);

        ProductDto result = productService.getProductById(1L);

        Assertions.assertEquals(productDto.getId(), result.getId());
        Assertions.assertEquals(productDto.getName(), result.getName());
        Assertions.assertEquals(productDto.getDescription(), result.getDescription());
        Assertions.assertEquals(productDto.getPrice(), result.getPrice());

    }


//    @Test
//    public void testCreateProduct() {
//
//        ProductSaveDto productSaveDto = new ProductSaveDto("Product1", "Description1", new BigDecimal(1111));
//
//        Products unSavedProduct = Products.builder()
//                .name(productSaveDto.getName()).description(productSaveDto.getDescription())
//                .price(productSaveDto.getPrice()).build();
//
//        Products savedProduct = Products.builder().id(1)
//                .name(productSaveDto.getName()).description(productSaveDto.getDescription())
//                .price(productSaveDto.getPrice()).build();
//
//        when(productRepository.save(unSavedProduct)).thenReturn(savedProduct);
//
//        ProductDto resultDto = productService.createProduct(productSaveDto);
//
//        Assertions.assertNotNull(resultDto);
//        Assertions.assertEquals(resultDto.getId(), savedProduct.getId());
//        Assertions.assertEquals(resultDto.getName(), savedProduct.getName());
//        Assertions.assertEquals(resultDto.getDescription(), savedProduct.getDescription());
//        Assertions.assertEquals(resultDto.getPrice(), savedProduct.getPrice());
//
//    }
//



}
