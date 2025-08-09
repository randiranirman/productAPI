package com.example.microservice.InventoryService.Dto;

public record InventoryRequest(String skuCode, int Quantity) {

    // No additional methods or logic needed for this record
    // It automatically provides getters for the fields Skucode and Quantity
}
