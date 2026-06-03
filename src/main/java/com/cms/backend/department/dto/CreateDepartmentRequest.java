package com.cms.backend.department.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateDepartmentRequest {

    @NotNull
    private Integer companyId;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 1000)
    private String description;
}