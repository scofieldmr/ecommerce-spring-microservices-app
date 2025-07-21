package com.ms.orderservice.service;

import com.ms.orderservice.client.InventoryClient;
import com.ms.orderservice.client.ProductClient;
import com.ms.orderservice.dto.OrderRequest;
import com.ms.orderservice.dto.OrderResponse;
import com.ms.orderservice.entity.Orders;
import com.ms.orderservice.exception.InventoryNotAvailableException;
import com.ms.orderservice.repo.OrderRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrderServiceImp implements OrderService {

    private static Logger log = LoggerFactory.getLogger(OrderServiceImp.class);

    private final OrderRepository orderRepository;

    private final InventoryClient inventoryClient;

    private final ProductClient productClient;

    public OrderServiceImp(OrderRepository orderRepository, InventoryClient inventoryClient, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
        this.productClient = productClient;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderDto) {

        var isStockAvailable = inventoryClient.isStockAvailable(orderDto.getSkuCode(),orderDto.getQuantity());

        if (!isStockAvailable) {
            log.error("Stock is not available for the product " + orderDto.getSkuCode());
            throw new InventoryNotAvailableException("Stock is not available for the product " + orderDto.getSkuCode());
        }

        var updatedStock = inventoryClient.updateInventoryStock(orderDto.getSkuCode(),orderDto.getQuantity());

        if(updatedStock == null){
            log.error("Product stock is not available for the product " + orderDto.getSkuCode());
            throw new InventoryNotAvailableException("Product stock is not available for the product " + orderDto.getSkuCode());
        }

        var productDetails = productClient.getProductDetailsByName(orderDto.getSkuCode());

        log.info("Stock is Available for the product " + orderDto.getSkuCode());
        Orders newOrder = new Orders();
        newOrder.setOrderNumber(UUID.randomUUID().toString());
        newOrder.setSkuCode(orderDto.getSkuCode());

        BigDecimal quantity = BigDecimal.valueOf(orderDto.getQuantity());
        BigDecimal totalPrice = productDetails.getPrice().multiply(quantity);

        newOrder.setPrice(totalPrice);
        newOrder.setQuantity(orderDto.getQuantity());

        Orders savedOrder = orderRepository.save(newOrder);

        return new OrderResponse(savedOrder.getOrderNumber(), savedOrder.getSkuCode(),
                savedOrder.getPrice(), "Order Placed Successfully");
    }
}
