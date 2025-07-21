package com.ms.orderservice.controller;

import com.ms.orderservice.dto.OrderRequest;
import com.ms.orderservice.dto.OrderResponse;
import com.ms.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) {
       OrderResponse orderResponse = orderService.placeOrder(orderRequest);
       return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }
}
