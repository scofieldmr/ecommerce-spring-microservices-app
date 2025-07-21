package com.ms.productservice.mapper;

import com.ms.productservice.dto.ProductDto;
import com.ms.productservice.dto.ProductSaveDto;
import com.ms.productservice.entity.Products;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {

    public Products productSaveToProduct(ProductSaveDto productSaveDto) {
        Products products = new Products();

        products.setName(productSaveDto.getName());
        products.setPrice(productSaveDto.getPrice());
        products.setDescription(productSaveDto.getDescription());
        return products;
    }

    public ProductDto productToProductDto(Products products) {
        ProductDto productDto = new ProductDto();
        productDto.setId(products.getId());
        productDto.setName(products.getName());
        productDto.setPrice(products.getPrice());
        productDto.setDescription(products.getDescription());
        return productDto;
    }
}
