package com.cms.backend.common.exception;

public record FieldErrorResponse(
        String field,
        String message
) {
}