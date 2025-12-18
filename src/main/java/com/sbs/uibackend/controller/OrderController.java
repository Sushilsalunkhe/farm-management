package com.sbs.uibackend.controller;

import com.sbs.uibackend.entity.Order;
import com.sbs.uibackend.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository repo;

    @PostMapping
    public Order placeOrder(
            @RequestBody Order order,
            Authentication auth) {

        order.setUsername(auth.getName());
        order.setOrderDate(LocalDateTime.now());
        order.getItems().forEach(item -> item.setOrder(order));

        return repo.save(order);
    }


    @GetMapping
    public List<Order> myOrders(Authentication authentication) {
        return repo.findByUsername(authentication.getName());
    }
}

