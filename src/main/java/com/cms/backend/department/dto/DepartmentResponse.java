package com.cms.backend.department.dto;

public record DepartmentResponse(
        Integer id,
        Integer companyId,
        String name,
        String description
) {}