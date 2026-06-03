package com.cms.backend.employee.dto;

public record EmployeeResponse(
        Integer id,
        String employeeCode,
        String firstName,
        String lastName,
        String departmentName,
        String positionName,
        String employmentStatus
) {}