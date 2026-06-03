package com.cms.backend.position.dto;

public record PositionResponse(
        Integer id,
        Integer companyId,
        String name,
        String description,
        Integer level,
        Boolean isActive
) {}