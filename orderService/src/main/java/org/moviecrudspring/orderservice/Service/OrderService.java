package org.moviecrudspring.orderservice.Service;


import lombok.RequiredArgsConstructor;
import org.moviecrudspring.orderservice.Client.InventoryClient;
import org.moviecrudspring.orderservice.Dtos.OrderRequest;
import org.moviecrudspring.orderservice.Models.Order;
import org.moviecrudspring.orderservice.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

@RequiredArgsConstructor

public class OrderService {


    private final OrderRepository orderRepository;

    private final InventoryClient inventoryClient;



    public void placeOrder( OrderRequest orderRequest) {

        // map order request for order model
        // save order in order repository
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if( isProductInStock) {
            var order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            order.setSkuCode(orderRequest.skuCode());


            orderRepository.save(order);

        }else {
            throw new RuntimeException("Product is not in stock");
        }





    }


    public List<OrderRequest> getAllOrders() {


        var orders = orderRepository.findAll();
        return orders.stream().map(order -> new OrderRequest(
                order.getId(),
                order.getOrderNumber(),
                order.getPrice(),
                order.getQuantity(),
                order.getSkuCode()
        )).toList();
    }
}
