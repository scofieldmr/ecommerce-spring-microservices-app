package com.ms.orderservice.service;

import com.ms.orderservice.dto.OrderRequest;
import com.ms.orderservice.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest orderDto);

}
