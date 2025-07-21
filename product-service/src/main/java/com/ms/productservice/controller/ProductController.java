package com.ms.productservice.controller;

import com.ms.productservice.dto.ProductDto;
import com.ms.productservice.dto.ProductSaveDto;
import com.ms.productservice.service.ProductService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createProductDetails(
            @RequestBody @Validated ProductSaveDto productSaveDto
            ){
        ProductDto createdProduct = productService.createProduct(productSaveDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/get/{product_id}")
    public ResponseEntity<?> getProductDetails(
            @NotNull @PathVariable("product_id") Long product_id
    ) {
        ProductDto getProductById = productService.getProductById(product_id);
        return new ResponseEntity<>(getProductById, HttpStatus.OK);
    }

    @GetMapping("/getProdByName/{product_name}")
    public ResponseEntity<?> getProductDetailsByName(
            @NotBlank @PathVariable("product_name") String productName
    ) {
        ProductDto getProductByName = productService.getProductByName(productName);
        return new ResponseEntity<>(getProductByName, HttpStatus.OK);
    }


}
