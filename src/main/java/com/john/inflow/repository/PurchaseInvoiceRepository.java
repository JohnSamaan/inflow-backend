package com.john.inflow.repository;

import com.john.inflow.entity.PurchaseInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PurchaseInvoiceRepository extends JpaRepository<PurchaseInvoice, Integer> {
    @Query("SELECT COALESCE(SUM(p.totalPrice), 0) FROM PurchaseInvoice p")
    BigDecimal sumTotalPrice();
}
