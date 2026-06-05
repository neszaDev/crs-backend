package com.cms.backend.position.dto;

import java.util.UUID;

public record PositionResponse(
        UUID id,
        String companyId,
        String name,
        String description,
        Integer level,
        Boolean isActive
) {}