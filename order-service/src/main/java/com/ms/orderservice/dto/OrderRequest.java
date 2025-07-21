package com.ms.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

    private String orderNumber;

    private String skuCode;

    private Integer quantity;

    public OrderRequest(String skuCode, Integer quantity) {
        this.skuCode = skuCode;
        this.quantity = quantity;
    }
}
