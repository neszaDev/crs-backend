package com.cms.backend.position.dto;

import lombok.Data;

@Data
public class CreatePositionRequest {

    private Integer companyId;

    private String name;

    private String description;

    private Integer level;
}