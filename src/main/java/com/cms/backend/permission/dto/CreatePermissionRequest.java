package com.cms.backend.permission.dto;

import jakarta.validation.constraints.NotBlank;

public record CreatePermissionRequest(

        @NotBlank
        String key,

        String description

) {}