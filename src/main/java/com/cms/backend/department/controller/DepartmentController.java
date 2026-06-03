package com.cms.backend.department.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cms.backend.department.dto.CreateDepartmentRequest;
import com.cms.backend.department.entity.Department;
import com.cms.backend.department.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(
            DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department createDepartment(
            @Valid @RequestBody CreateDepartmentRequest request) {

        return departmentService.createDepartment(request);
    }
}