package com.john.inflow.dto.response;

/**
 * Purpose: Lightweight DTO for embedding inside other DTOs
 */
public record CategorySummaryDTO(
        Integer id,
        String name
) {}