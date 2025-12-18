package com.sbs.uibackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FarmEmployee extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String employeeName;

    private String role;

    private Double salary;

    private String mobile;

    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;
}

