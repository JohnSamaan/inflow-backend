package com.john.inflow.service;

import com.john.inflow.dto.response.*;
import com.john.inflow.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReportService {
    private final ProductWarehouseRepository productWarehouseRepository;
    private final SalesInvoiceRepository salesInvoiceRepository;
    private final PurchaseInvoiceRepository purchaseInvoiceRepository;
    private final ReturnSalesInvoiceRepository returnSalesInvoiceRepository;
    private final ReturnPurchaseInvoiceRepository returnPurchaseInvoiceRepository;

    public ReportService(ProductWarehouseRepository productWarehouseRepository,
                         SalesInvoiceRepository salesInvoiceRepository,
                         PurchaseInvoiceRepository purchaseInvoiceRepository,
                         ReturnSalesInvoiceRepository returnSalesInvoiceRepository,
                         ReturnPurchaseInvoiceRepository returnPurchaseInvoiceRepository) {
        this.productWarehouseRepository = productWarehouseRepository;
        this.salesInvoiceRepository = salesInvoiceRepository;
        this.purchaseInvoiceRepository = purchaseInvoiceRepository;
        this.returnSalesInvoiceRepository = returnSalesInvoiceRepository;
        this.returnPurchaseInvoiceRepository = returnPurchaseInvoiceRepository;
    }

    public List<StockOnHandResponse> getStockOnHand() {
        return productWarehouseRepository.getStockOnHand();
    }

    public SalesSummaryResponse getSalesSummary() {
        long count = salesInvoiceRepository.count();
        BigDecimal total = salesInvoiceRepository.sumTotalPrice();
        return new SalesSummaryResponse(count, total);
    }

    public PurchaseSummaryResponse getPurchaseSummary() {
        long count = purchaseInvoiceRepository.count();
        BigDecimal total = purchaseInvoiceRepository.sumTotalPrice();
        return new PurchaseSummaryResponse(count, total);
    }

    public ReturnsSummaryResponse getReturnsSummary() {
        long salesCount = returnSalesInvoiceRepository.count();
        BigDecimal salesTotal = returnSalesInvoiceRepository.sumTotalPrice();
        long purchasesCount = returnPurchaseInvoiceRepository.count();
        BigDecimal purchasesTotal = returnPurchaseInvoiceRepository.sumTotalPrice();
        return new ReturnsSummaryResponse(salesCount, salesTotal, purchasesCount, purchasesTotal);
    }

    public List<CustomerPurchaseHistoryResponse> getCustomerPurchaseHistory() {
        return salesInvoiceRepository.getCustomerPurchaseHistoryRaw().stream()
                .map(row -> new CustomerPurchaseHistoryResponse(
                        (Integer) row[0],
                        (String) row[1],
                        row[2] != null ? new BigDecimal(row[2].toString()) : BigDecimal.ZERO,
                        BigDecimal.ZERO
                ))
                .toList();
    }
}
