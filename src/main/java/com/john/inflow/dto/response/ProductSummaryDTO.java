package com.john.inflow.dto.response;

import java.math.BigDecimal;

/**
 * Purpose: Lightweight DTO for lists, dropdowns, embedding in other DTOs
 */
public record ProductSummaryDTO(
        Integer id,
        String name,
        BigDecimal currentPrice
) {}