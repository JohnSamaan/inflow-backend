package com.john.inflow.controller;

import com.john.inflow.dto.request.StockAdjustmentRequest;
import com.john.inflow.dto.request.StockCountRequest;
import com.john.inflow.dto.response.StockOnHandResponse;
import com.john.inflow.service.StockService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<StockOnHandResponse>> getAllStock() {
        return ResponseEntity.ok(stockService.getAllStock());
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<StockOnHandResponse>> getStockByWarehouse(@PathVariable Integer warehouseId) {
        return ResponseEntity.ok(stockService.getStockByWarehouse(warehouseId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<StockOnHandResponse>> getStockByProduct(@PathVariable Integer productId) {
        return ResponseEntity.ok(stockService.getStockByProduct(productId));
    }

    @PostMapping("/adjustments")
    public ResponseEntity<Void> adjustStock(@Valid @RequestBody StockAdjustmentRequest request) {
        stockService.adjustStock(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/counts")
    public ResponseEntity<Void> updateStockCount(@Valid @RequestBody StockCountRequest request) {
        stockService.updateStockCount(request);
        return ResponseEntity.ok().build();
    }
}
