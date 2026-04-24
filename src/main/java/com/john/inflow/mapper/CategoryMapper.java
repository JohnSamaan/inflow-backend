package com.john.inflow.mapper;

import com.john.inflow.dto.request.CreateCategoryRequestDTO;
import com.john.inflow.dto.response.CategoryResponseDTO;
import com.john.inflow.dto.response.CategorySummaryDTO;
import com.john.inflow.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Purpose: Mapper for Category DTOs
 */
@Component
public class CategoryMapper {

    public Category toEntity(CreateCategoryRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Category category = new Category();
        category.setName(dto.name());
        return category;
    }

    public CategoryResponseDTO toResponseDTO(Category entity) {
        if (entity == null) {
            return null;
        }
        return new CategoryResponseDTO(
                entity.getId(),
                entity.getName()
        );
    }

    public CategorySummaryDTO toSummaryDTO(Category entity) {
        if (entity == null) {
            return null;
        }
        return new CategorySummaryDTO(
                entity.getId(),
                entity.getName()
        );
    }

    public List<CategoryResponseDTO> toResponseDTOList(List<Category> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<CategorySummaryDTO> toSummaryDTOList(List<Category> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toSummaryDTO)
                .collect(Collectors.toList());
    }
}