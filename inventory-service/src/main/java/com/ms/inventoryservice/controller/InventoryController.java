package com.ms.inventoryservice.controller;

import com.ms.inventoryservice.dto.InventoryUpdatedResponse;
import com.ms.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/stockAvailable/{sku_code}/{quantity}")
    public ResponseEntity<?> isStockAvailable(
            @PathVariable("sku_code") String skuCode, @PathVariable("quantity") Integer quantity){

        boolean stockAvailable = inventoryService.isStockAvailable(skuCode, quantity);
        if(stockAvailable){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/updateInventoryStock/{sku_code}/{quantity}")
    public ResponseEntity<?> updateInventoryStock(
            @PathVariable("sku_code") String skuCode, @PathVariable("quantity") Integer quantity){

        InventoryUpdatedResponse stockUpdated = inventoryService.updateStock(skuCode,quantity);

        if(stockUpdated==null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stockUpdated, HttpStatus.OK);


    }


}
