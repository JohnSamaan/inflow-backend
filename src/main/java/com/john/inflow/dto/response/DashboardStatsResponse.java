package com.john.inflow.dto.response;

import java.math.BigDecimal;

public record DashboardStatsResponse(
    long totalProducts,
    long totalCustomers,
    BigDecimal totalSales,
    BigDecimal totalPurchases
) {}
