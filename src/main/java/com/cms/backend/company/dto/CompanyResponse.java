package com.cms.backend.company.dto;

public record CompanyResponse(
        Integer id,
        String name,
        String subscriptionPlan,
        String status
) {}