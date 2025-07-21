package com.ms.productservice.service;

import com.ms.productservice.dto.ProductDto;
import com.ms.productservice.dto.ProductSaveDto;

import java.util.List;

public interface ProductService {

    ProductDto getProductById(long productId);

    List<ProductDto> getAllProducts();

    ProductDto createProduct(ProductSaveDto productDto);

    ProductDto getProductByName(String skuCode);
}
