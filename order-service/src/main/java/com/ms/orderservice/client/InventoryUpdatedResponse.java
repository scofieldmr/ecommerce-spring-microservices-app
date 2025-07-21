package com.ms.orderservice.client;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryUpdatedResponse {

    private String skuCode;
    private Integer quantity;
    private String message;
}
