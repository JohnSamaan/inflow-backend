package com.john.inflow.service;

import com.john.inflow.dto.request.StockAdjustmentRequest;
import com.john.inflow.dto.request.StockCountRequest;
import com.john.inflow.dto.response.StockOnHandResponse;
import com.john.inflow.entity.ProductWarehouse;
import com.john.inflow.entity.ProductWarehouseId;
import com.john.inflow.repository.ProductWarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    private final ProductWarehouseRepository productWarehouseRepository;

    public StockService(ProductWarehouseRepository productWarehouseRepository) {
        this.productWarehouseRepository = productWarehouseRepository;
    }

    public List<StockOnHandResponse> getAllStock() {
        return productWarehouseRepository.getStockOnHand();
    }

    public List<StockOnHandResponse> getStockByWarehouse(Integer warehouseId) {
        return productWarehouseRepository.getStockOnHand().stream()
            .filter(s -> s.warehouseId().equals(warehouseId))
            .toList();
    }

    public List<StockOnHandResponse> getStockByProduct(Integer productId) {
        return productWarehouseRepository.getStockOnHand().stream()
            .filter(s -> s.productId().equals(productId))
            .toList();
    }

    public void adjustStock(StockAdjustmentRequest request) {
        ProductWarehouseId id = new ProductWarehouseId(request.productId(), request.warehouseId());
        ProductWarehouse pw = productWarehouseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Stock record not found"));
        int newAmount = pw.getAmount() + request.adjustmentAmount();
        if (newAmount < 0) {
            throw new RuntimeException("Stock cannot drop below 0");
        }
        pw.setAmount(newAmount);
        productWarehouseRepository.save(pw);
    }

    public void updateStockCount(StockCountRequest request) {
        ProductWarehouseId id = new ProductWarehouseId(request.productId(), request.warehouseId());
        ProductWarehouse pw = productWarehouseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Stock record not found"));
        pw.setAmount(request.countedAmount());
        productWarehouseRepository.save(pw);
    }
}
