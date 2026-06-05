package com.cms.backend.permission.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePermissionRequest(

        @NotBlank
        String key,

        String description

) {}