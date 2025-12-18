package com.sbs.uibackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FarmProductDTO {

    private Long id;

    @NotNull
    private String productName;
    private String category;
    private Double price;
    private Integer quantity;
    private Long farmId;
}


