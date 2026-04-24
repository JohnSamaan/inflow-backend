package com.john.inflow.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Purpose: Accept data for creating a new category
 */
public record CreateCategoryRequestDTO(
        @NotBlank(message = "Category name must not be blank")
        @Size(max = 255, message = "Category name must not exceed 255 characters")
        String name
) {}