package com.sbs.uibackend.controller;

import com.sbs.uibackend.entity.Order;
import com.sbs.uibackend.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/my")
    public ResponseEntity<?> myOrders(Authentication auth) {

        if (auth == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("User not authenticated");
        }

        String username = auth.getName();
        return ResponseEntity.ok(repo.findByUsername(username));
    }

}

