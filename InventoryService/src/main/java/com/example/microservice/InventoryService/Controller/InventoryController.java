package com.example.microservice.InventoryService.Controller;


import com.example.microservice.InventoryService.Dto.InventoryRequest;
import com.example.microservice.InventoryService.Models.Inventory;
import com.example.microservice.InventoryService.Services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/inventory")
@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;




    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createInventory(@RequestBody InventoryRequest inventoryRequest) {

        inventoryService.createInventory(inventoryRequest.SkuCode(), inventoryRequest.Quantity());

        return "Inventory created successfully for SKU: " + inventoryRequest.SkuCode();





    }
}
