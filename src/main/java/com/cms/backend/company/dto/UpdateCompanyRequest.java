package com.cms.backend.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCompanyRequest {

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String subscriptionPlan;

    @NotBlank
    @Size(max = 20)
    private String status;
}