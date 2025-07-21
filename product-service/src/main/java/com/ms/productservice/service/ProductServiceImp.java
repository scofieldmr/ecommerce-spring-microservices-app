package com.ms.productservice.service;

import com.ms.productservice.dto.ProductDto;
import com.ms.productservice.dto.ProductSaveDto;
import com.ms.productservice.entity.Products;
import com.ms.productservice.exception.ProductIdNotFoundException;
import com.ms.productservice.mapper.ProductMapper;
import com.ms.productservice.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductServiceImp(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    public ProductDto getProductById(long productId) {

        Products findProdById = productRepository.findById(productId)
                .orElseThrow(()-> new ProductIdNotFoundException("Product id not found in DB : " + productId));

        ProductDto productDto = productMapper.productToProductDto(findProdById);

        return productDto;
    }

    @Override
    public List<ProductDto> getAllProducts() {

        List<Products> productsList = productRepository.findAll();

        List<ProductDto> productDtoList = new ArrayList<>();
        for (Products products : productsList) {
            ProductDto productDto = productMapper.productToProductDto(products);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public ProductDto createProduct(ProductSaveDto productDto) {

        Products addProduct = productMapper.productSaveToProduct(productDto);

        Products savedProduct = productRepository.save(addProduct);

        return productMapper.productToProductDto(savedProduct);
    }

    @Override
    public ProductDto getProductByName(String skuCode) {

        Products getProductByName = productRepository.findProductsByName(skuCode)
                .orElseThrow(()-> new ProductIdNotFoundException("Product not found in DB : " + skuCode));

        ProductDto productDto = productMapper.productToProductDto(getProductByName);

        return productDto;
    }


}
