package com.sbs.uibackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    private Long productId;
    private String productName;
    private BigDecimal price;
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    private Order order;
}

