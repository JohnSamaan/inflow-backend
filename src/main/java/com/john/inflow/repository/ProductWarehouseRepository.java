package com.john.inflow.repository;

import com.john.inflow.entity.ProductWarehouse;
import com.john.inflow.entity.ProductWarehouseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductWarehouseRepository extends JpaRepository<ProductWarehouse, ProductWarehouseId> {
    @Query("SELECT new com.john.inflow.dto.response.StockOnHandResponse(p.id, p.name, w.id, w.address, pw.amount) FROM ProductWarehouse pw JOIN pw.product p JOIN pw.warehouse w")
    List<com.john.inflow.dto.response.StockOnHandResponse> getStockOnHand();

    List<ProductWarehouse> findByWarehouseId(Integer warehouseId);
}
