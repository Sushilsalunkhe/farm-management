package com.sbs.uibackend.component;

import com.sbs.uibackend.dto.FarmProductDTO;
import com.sbs.uibackend.entity.Farm;
import com.sbs.uibackend.entity.FarmProduct;
import org.springframework.stereotype.Component;

@Component
public class FarmProductMapper {

    public FarmProduct toEntity(FarmProductDTO dto, Farm farm) {
        FarmProduct product = new FarmProduct();
        product.setProductName(dto.getProductName());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setFarm(farm);
        return product;
    }

    public FarmProductDTO toDTO(FarmProduct product) {
        FarmProductDTO dto = new FarmProductDTO();
        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setFarmId(product.getFarm().getId());
        return dto;
    }
}

