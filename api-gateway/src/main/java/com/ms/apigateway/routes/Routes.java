package com.ms.apigateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.*;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class Routes {

    @Value("${product.service.url}")
    private String productServiceUrl;

    @Value("${order.service.url}")
    private String orderServiceUrl;

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute(){
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/v1/products/**"), HandlerFunctions.http(productServiceUrl))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> swaggerProductServiceRoute(){
        return GatewayRouterFunctions.route("swagger_product_service")
                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"),HandlerFunctions.http(productServiceUrl))
                .filter(setPath("/v3/api-docs")).build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute(){
        return GatewayRouterFunctions.route("order_service")
                .route(RequestPredicates.path("/api/v1/orders/**"), HandlerFunctions.http(orderServiceUrl))
        .build();
    }

    @Bean
    public RouterFunction<ServerResponse> swaggerOrderServiceRoute(){
        return GatewayRouterFunctions.route("swagger_order_service")
                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"),HandlerFunctions.http(orderServiceUrl))
                .filter(setPath("/v3/api-docs")).build();
    }


    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute(){
        return GatewayRouterFunctions.route("inventory_service")
                .route(RequestPredicates.path("/api/v1/inventory/**"),HandlerFunctions.http(inventoryServiceUrl))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> swaggerInventoryServiceRoute(){
        return GatewayRouterFunctions.route("swagger_inventory_service")
                .route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"),HandlerFunctions.http(inventoryServiceUrl))
                .filter(setPath("/v3/api-docs")).build();
    }

}
