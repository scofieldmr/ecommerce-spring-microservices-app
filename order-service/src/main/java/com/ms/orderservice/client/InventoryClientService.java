//package com.ms.orderservice.client;
//
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.retry.annotation.Retry;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//@Service
//public class InventoryClientService {
//
//        private static final Logger log = LoggerFactory.getLogger(InventoryClientService.class);
//
//        private final InventoryClient inventoryClient;
//
//    public InventoryClientService(InventoryClient inventoryClient) {
//        this.inventoryClient = inventoryClient;
//    }
//
//       @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackIsStockAvailable")
//        @Retry(name = "inventory")
//        public boolean isStockAvailable(String skuCode, int quantity) {
//            return inventoryClient.isStockAvailable(skuCode, quantity);
//        }
//
//        public boolean fallbackIsStockAvailable(String skuCode, int quantity, Throwable t) {
//            log.warn("Fallback triggered for stock check [{}:{}]: {}", skuCode, quantity, t.getMessage());
//            return false;
//        }
//
//        @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackUpdateStock")
//        @Retry(name = "inventory")
//        public InventoryUpdatedResponse updateInventoryStock(String skuCode, int quantity) {
//            return inventoryClient.updateInventoryStock(skuCode, quantity);
//        }
//
//        public boolean fallbackUpdateStock(String skuCode, int quantity, Throwable t) {
//            log.warn("Fallback triggered for stock update [{}:{}]: {}", skuCode, quantity, t.getMessage());
//            return false; // or your default response
//        }
//
//}
