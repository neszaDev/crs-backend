package com.cms.backend.employee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cms.backend.employee.dto.EmployeeResponse;
import com.cms.backend.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(
            EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}