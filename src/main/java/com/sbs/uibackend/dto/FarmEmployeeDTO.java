package com.sbs.uibackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmEmployeeDTO {
    private Long id;
    private String employeeName;
    private String role;
    private Double salary;
    private String mobile;
    private Long farmId;
}

