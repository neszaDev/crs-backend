package com.cms.backend.permission.dto;

import java.util.UUID;

public record PermissionResponse(
        UUID id,
        String key,
        String description
) {}