package com.ms.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.orderservice.dto.OrderRequest;
import com.ms.orderservice.dto.OrderResponse;
import com.ms.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
public class TestOrderController {

    @MockitoBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testPlaceOrder() throws Exception {
        OrderRequest orderRequest = new OrderRequest("OrderNumber1","SKU123",1);

        OrderResponse orderResponse = new OrderResponse("OrderNumber1","SKU123","Order Placed Successfully");

        when(orderService.placeOrder(orderRequest)).thenReturn(orderResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orders/placeOrder")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderNumber").value("OrderNumber1"))
                .andExpect(jsonPath("$.skuCode").value("SKU123"))
                .andExpect(jsonPath("$.message").value("Order Placed Successfully"));


    }
}
