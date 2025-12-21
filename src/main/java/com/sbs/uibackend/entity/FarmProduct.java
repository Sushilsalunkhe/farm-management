package com.sbs.uibackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FarmProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private String category;

    private Double price;

    private Integer quantity;
    
    private String description;

    @Column(name = "image_url")
    private String imageUrl;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "farm_id")
    private Farm farm;

}

