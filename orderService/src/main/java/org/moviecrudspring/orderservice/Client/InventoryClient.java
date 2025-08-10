package org.moviecrudspring.orderservice.Client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory" , url = "http://localhost:8082")
public interface InventoryClient {

    @RequestMapping( method = RequestMethod.GET , value = "/api/inventory/inventory-check")
    boolean isInStock(@RequestParam String skuCode, @RequestParam int quantity);


}
