package com.example.microservice.InventoryService.Services;


import com.example.microservice.InventoryService.Models.Inventory;
import com.example.microservice.InventoryService.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InventoryService {


    private final InventoryRepository inventoryRepository;


    public void  createInventory(String skuCode, int quantity) {
        // Logic to create inventory
        // This could involve checking if the SKU already exists, etc.
        // For now, we will just print the details
        System.out.println("Creating inventory for SKU: " + skuCode + " with quantity: " + quantity);

        Inventory inventory = new Inventory();
        inventory.setSkuCode(skuCode);
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);



    }


}
