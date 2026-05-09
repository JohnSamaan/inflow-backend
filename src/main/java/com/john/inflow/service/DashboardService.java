package com.john.inflow.service;

import com.john.inflow.dto.response.DashboardStatsResponse;
import com.john.inflow.repository.CustomerRepository;
import com.john.inflow.repository.ProductRepository;
import com.john.inflow.repository.PurchaseInvoiceRepository;
import com.john.inflow.repository.SalesInvoiceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DashboardService {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final SalesInvoiceRepository salesInvoiceRepository;
    private final PurchaseInvoiceRepository purchaseInvoiceRepository;

    public DashboardService(ProductRepository productRepository, CustomerRepository customerRepository,
                            SalesInvoiceRepository salesInvoiceRepository, PurchaseInvoiceRepository purchaseInvoiceRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.salesInvoiceRepository = salesInvoiceRepository;
        this.purchaseInvoiceRepository = purchaseInvoiceRepository;
    }

    public DashboardStatsResponse getStats() {
        long totalProducts = productRepository.count();
        long totalCustomers = customerRepository.count();
        BigDecimal totalSales = salesInvoiceRepository.sumTotalPrice();
        BigDecimal totalPurchases = purchaseInvoiceRepository.sumTotalPrice();

        return new DashboardStatsResponse(totalProducts, totalCustomers, totalSales, totalPurchases);
    }
}
