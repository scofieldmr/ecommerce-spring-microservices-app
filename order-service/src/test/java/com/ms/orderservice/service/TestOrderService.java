package com.ms.orderservice.service;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.ms.orderservice.client.InventoryClient;
import com.ms.orderservice.dto.OrderRequest;
import com.ms.orderservice.dto.OrderResponse;
import com.ms.orderservice.entity.Orders;
import com.ms.orderservice.repo.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoBeans;

import java.math.BigDecimal;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureWireMock(port = 0)  // Use dynamic port so it doesn’t conflict
@ActiveProfiles("test")          // Assuming you have test profile for config overrides
public class TestOrderService {

    @Mock
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImp orderService;

    @Test
    public void testPlaceOrder() {
        OrderRequest orderRequest = new OrderRequest("OrderNumber1","SkuCode1",1);

        InventoryClientStub.stubInventoryCall(orderRequest.getSkuCode(),orderRequest.getQuantity());

//        stubFor(get(urlEqualTo("/api/v1/inventory/stockAvailable/" + orderRequest.getSkuCode() + "/" + orderRequest.getQuantity()))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("true")));

        Orders newOrder = new Orders();
        newOrder.setOrderNumber("OrderNumber1");
        newOrder.setSkuCode("SkuCode1");
        newOrder.setQuantity(1);

        Orders savedOrder =  new Orders(1L,"OrderNumber1","SkuCode1",new BigDecimal(1.0),1);

        when(orderRepository.save(ArgumentMatchers.any(Orders.class))).thenReturn(savedOrder);

        OrderResponse orderResponse = new OrderResponse(savedOrder.getOrderNumber(),savedOrder.getSkuCode(),"Order Placed Successfully");

        OrderResponse result = orderService.placeOrder(orderRequest);

        Assertions.assertEquals(result.getSkuCode(),savedOrder.getSkuCode());

    }
}
