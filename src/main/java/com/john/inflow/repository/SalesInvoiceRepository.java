package com.john.inflow.repository;

import com.john.inflow.entity.SalesInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Integer> {
    @Query("SELECT COALESCE(SUM(s.totalPrice), 0) FROM SalesInvoice s")
    BigDecimal sumTotalPrice();

    @Query("SELECT c.id, c.name, COALESCE(SUM(s.totalPrice), 0) FROM Customer c LEFT JOIN c.salesInvoices s GROUP BY c.id, c.name")
    List<Object[]> getCustomerPurchaseHistoryRaw();
}
