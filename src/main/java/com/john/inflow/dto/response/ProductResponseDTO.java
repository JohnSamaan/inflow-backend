package com.john.inflow.dto.response;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Purpose: Return product data in API responses
 */
public record ProductResponseDTO(
        Integer id,
        String name,
        String description,
        String pictureUrl,
        BigDecimal currentPrice,
        SupplierSummaryDTO supplier,
        Set<CategorySummaryDTO> categories
) {
    /**
     * Inner record for supplier summary.
     */
    public record SupplierSummaryDTO(
            Integer id,
            String name
    ) {}
}