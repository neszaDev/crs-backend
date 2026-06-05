package com.cms.backend.role.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateRoleRequest(
    @NotBlank
    String name
) {
}