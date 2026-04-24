package com.john.inflow.mapper;

import com.john.inflow.dto.request.CreateProductRequestDTO;
import com.john.inflow.dto.response.CategorySummaryDTO;
import com.john.inflow.dto.response.ProductResponseDTO;
import com.john.inflow.dto.response.ProductSummaryDTO;
import com.john.inflow.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Purpose: Mapper for Product DTOs
 */
@Component
public class ProductMapper {

    public Product toEntity(CreateProductRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPictureUrl(dto.pictureUrl());
        product.setCurrentPrice(dto.currentPrice());
        return product;
    }

    public ProductResponseDTO toResponseDTO(Product entity) {
        if (entity == null) {
            return null;
        }
        
        ProductResponseDTO.SupplierSummaryDTO supplierDto = null;
        if (entity.getSupplier() != null) {
            supplierDto = new ProductResponseDTO.SupplierSummaryDTO(
                    entity.getSupplier().getId(),
                    entity.getSupplier().getName()
            );
        }
        
        Set<CategorySummaryDTO> categoryDtos = null;
        if (entity.getCategories() != null) {
            categoryDtos = entity.getCategories().stream()
                    .map(category -> new CategorySummaryDTO(category.getId(), category.getName()))
                    .collect(Collectors.toSet());
        }

        return new ProductResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPictureUrl(),
                entity.getCurrentPrice(),
                supplierDto,
                categoryDtos
        );
    }

    public ProductSummaryDTO toSummaryDTO(Product entity) {
        if (entity == null) {
            return null;
        }
        return new ProductSummaryDTO(
                entity.getId(),
                entity.getName(),
                entity.getCurrentPrice()
        );
    }

    public List<ProductResponseDTO> toResponseDTOList(List<Product> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ProductSummaryDTO> toSummaryDTOList(List<Product> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toSummaryDTO)
                .collect(Collectors.toList());
    }
}