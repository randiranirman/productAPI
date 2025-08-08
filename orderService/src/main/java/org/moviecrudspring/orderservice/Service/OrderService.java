package org.moviecrudspring.orderservice.Service;


import lombok.RequiredArgsConstructor;
import org.moviecrudspring.orderservice.Dtos.OrderRequest;
import org.moviecrudspring.orderservice.Models.Order;
import org.moviecrudspring.orderservice.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

@RequiredArgsConstructor

public class OrderService {


    private final OrderRepository orderRepository;


    public void placeOrder( OrderRequest orderRequest) {

        // map order request for order model
        // save order in order repository

        var order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        order.setSkuCode(orderRequest.skuCode());


        orderRepository.save(order);



    }
}
