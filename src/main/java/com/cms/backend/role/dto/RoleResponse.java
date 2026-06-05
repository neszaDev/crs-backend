package com.cms.backend.role.dto;

import java.util.UUID;

public record RoleResponse(
    UUID id,
    String name
) {
}