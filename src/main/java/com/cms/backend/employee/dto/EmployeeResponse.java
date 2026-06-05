package com.cms.backend.employee.dto;

import java.util.UUID;

public record EmployeeResponse(
        UUID id,
        String employeeCode,
        String firstName,
        String lastName,
        String departmentName,
        String positionName,
        String employmentStatus
) {}