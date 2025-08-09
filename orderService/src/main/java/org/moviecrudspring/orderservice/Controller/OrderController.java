package org.moviecrudspring.orderservice.Controller;


import lombok.AllArgsConstructor;
import org.moviecrudspring.orderservice.Dtos.OrderRequest;
import org.moviecrudspring.orderservice.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor


public class OrderController {



    private  final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public String placeOrder(@RequestBody OrderRequest orderRequest) {

        orderService.placeOrder(orderRequest);

        return "Order placed successfully";

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderRequest> getAllOrders() {
        return orderService.getAllOrders();

    }


}
