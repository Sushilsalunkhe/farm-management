package com.sbs.uibackend.controller;

import com.sbs.uibackend.entity.FarmEmployee;
import com.sbs.uibackend.service.FarmEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class FarmEmployeeController {

    @Autowired
    private FarmEmployeeService employeeService;

    @PostMapping
    public FarmEmployee addEmployee(@RequestBody FarmEmployee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/farm/{farmId}")
    public List<FarmEmployee> getEmployeesByFarm(@PathVariable Long farmId) {
        return employeeService.getEmployeesByFarm(farmId);
    }
}

