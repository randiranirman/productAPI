package com.example.microservice.InventoryService.Repository;

import com.example.microservice.InventoryService.Models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {


    boolean existsBySkuCodeAndQuantityGreaterThanEqual(String skuCode, int quantity);
}
