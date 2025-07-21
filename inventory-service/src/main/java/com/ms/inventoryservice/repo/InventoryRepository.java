package com.ms.inventoryservice.repo;

import com.ms.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);

    Optional<Inventory> findInventoryBySkuCode(String skuCode);
}
