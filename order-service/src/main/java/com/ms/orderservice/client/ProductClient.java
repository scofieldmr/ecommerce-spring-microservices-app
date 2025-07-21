package com.ms.orderservice.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface ProductClient {

    @GetExchange("/api/v1/products/getProdByName/{product_name}")
    ProductResponseDto getProductDetailsByName(@PathVariable("product_name") String product_name);
}
