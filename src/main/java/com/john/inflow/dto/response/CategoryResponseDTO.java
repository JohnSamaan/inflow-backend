package com.john.inflow.dto.response;

/**
 * Purpose: Return category data in API responses
 */
public record CategoryResponseDTO(
        Integer id,
        String name
) {}