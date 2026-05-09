package com.john.inflow.controller;

import com.john.inflow.dto.response.*;
import com.john.inflow.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/stock-on-hand")
    public ResponseEntity<List<StockOnHandResponse>> getStockOnHand() {
        return ResponseEntity.ok(reportService.getStockOnHand());
    }

    @GetMapping("/sales-summary")
    public ResponseEntity<SalesSummaryResponse> getSalesSummary() {
        return ResponseEntity.ok(reportService.getSalesSummary());
    }

    @GetMapping("/purchase-summary")
    public ResponseEntity<PurchaseSummaryResponse> getPurchaseSummary() {
        return ResponseEntity.ok(reportService.getPurchaseSummary());
    }

    @GetMapping("/returns")
    public ResponseEntity<ReturnsSummaryResponse> getReturnsSummary() {
        return ResponseEntity.ok(reportService.getReturnsSummary());
    }

    @GetMapping("/customer-purchase-history")
    public ResponseEntity<List<CustomerPurchaseHistoryResponse>> getCustomerPurchaseHistory() {
        return ResponseEntity.ok(reportService.getCustomerPurchaseHistory());
    }
}
