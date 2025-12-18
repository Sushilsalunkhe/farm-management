package com.sbs.uibackend.service;

import com.sbs.uibackend.entity.FarmEmployee;
import com.sbs.uibackend.entity.FarmProduct;
import com.sbs.uibackend.repo.FarmEmployeeRepository;
import com.sbs.uibackend.repo.FarmProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmEmployeeService {

    @Autowired
    private FarmEmployeeRepository employeeRepository;

    public FarmEmployee saveEmployee(FarmEmployee employee) {
        return employeeRepository.save(employee);
    }

    public List<FarmEmployee> getEmployeesByFarm(Long farmId) {
        return employeeRepository.findByFarmId(farmId);
    }
}
