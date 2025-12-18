package com.sbs.uibackend.component;

import com.sbs.uibackend.dto.FarmEmployeeDTO;
import com.sbs.uibackend.entity.Farm;
import com.sbs.uibackend.entity.FarmEmployee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public FarmEmployee toEntity(FarmEmployeeDTO dto, Farm farm) {
        if (dto == null) return null;

        FarmEmployee employee = new FarmEmployee();
        employee.setEmployeeName(dto.getEmployeeName());
        employee.setMobile(dto.getMobile());
        employee.setRole(dto.getRole());
        employee.setSalary(dto.getSalary());
        employee.setFarm(farm);

        return employee;
    }

    public FarmEmployeeDTO toDTO(FarmEmployee employee) {
        if (employee == null) return null;

        FarmEmployeeDTO dto = new FarmEmployeeDTO();
        dto.setId(employee.getId());
        dto.setEmployeeName(employee.getEmployeeName());
        dto.setRole(employee.getRole());
        dto.setMobile(employee.getMobile());
        dto.setSalary(employee.getSalary());
        dto.setSalary(employee.getSalary());

        if (employee.getFarm() != null) {
            dto.setFarmId(employee.getFarm().getId());
        }

        return dto;
    }
}

