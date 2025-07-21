package com.ms.orderservice.exception;

public class InventoryNotAvailableException extends RuntimeException {
    public InventoryNotAvailableException(String message) {
        super(message);
    }
}
