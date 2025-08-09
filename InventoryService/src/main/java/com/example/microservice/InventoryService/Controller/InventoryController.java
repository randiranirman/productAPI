package com.example.microservice.InventoryService.Controller;


import com.example.microservice.InventoryService.Dto.InventoryRequest;
import com.example.microservice.InventoryService.Models.Inventory;
import com.example.microservice.InventoryService.Services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/inventory")
@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;




    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryRequest createInventory(@RequestBody InventoryRequest inventoryRequest) {




            var response = inventoryService.createInventory(inventoryRequest.skuCode(), inventoryRequest.Quantity());


        return response;





    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryRequest> getAllRequest() {

        return inventoryService.getAllRequest();

    }


    @GetMapping("/inventory-check")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam int quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }
}
