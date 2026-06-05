package com.cms.backend.company.dto;

import java.util.UUID;

public record CompanyResponse(
        UUID id,
        String name,
        String subscriptionPlan,
        String status
) {}