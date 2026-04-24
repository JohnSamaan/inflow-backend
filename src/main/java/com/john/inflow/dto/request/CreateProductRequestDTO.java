package com.john.inflow.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Purpose: Accept data for creating a new product
 */
public record CreateProductRequestDTO(
        @NotBlank(message = "Product name must not be blank")
        @Size(max = 255, message = "Product name must not exceed 255 characters")
        String name,

        @Size(max = 2000, message = "Product description must not exceed 2000 characters")
        String description,

        String pictureUrl,

        @NotNull(message = "Current price must not be null")
        @DecimalMin(value = "0.0", inclusive = true, message = "Current price must be greater than or equal to 0")
        BigDecimal currentPrice,

        Integer supplierId,

        Set<Integer> categoryIds
) {}