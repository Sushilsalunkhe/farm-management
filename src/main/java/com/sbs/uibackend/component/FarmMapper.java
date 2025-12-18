package com.sbs.uibackend.component;

import com.sbs.uibackend.dto.FarmDto;
import com.sbs.uibackend.entity.Farm;
import org.springframework.stereotype.Component;

@Component
public class FarmMapper {

    public Farm toEntity(FarmDto dto) {
        if (dto == null) return null;

        Farm farm = new Farm();
        farm.setFarmName(dto.getFarmName());
        farm.setLocation(dto.getLocation());
        farm.setArea(dto.getArea());
        farm.setFarmType(dto.getFarmType());

        return farm;
    }

    public FarmDto toDTO(Farm farm) {
        if (farm == null) return null;

        FarmDto dto = new FarmDto();
        dto.setId(farm.getId());
        dto.setArea(farm.getArea());
        dto.setLocation(farm.getLocation());
        dto.setFarmName(farm.getFarmName());
        dto.setFarmType(farm.getFarmType());

        return dto;
    }
}

