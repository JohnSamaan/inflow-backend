package com.john.inflow.controller;

import com.john.inflow.dto.request.AssignUserWarehouseRequest;
import com.john.inflow.dto.request.WarehouseRequest;
import com.john.inflow.dto.response.ProductWarehouseResponse;
import com.john.inflow.dto.response.UserResponse;
import com.john.inflow.dto.response.WarehouseResponse;
import com.john.inflow.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public ResponseEntity<WarehouseResponse> create(@Valid @RequestBody WarehouseRequest request) {
        WarehouseResponse response = warehouseService.create(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(warehouseService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<WarehouseResponse>> getAll() {
        return ResponseEntity.ok(warehouseService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseResponse> update(@PathVariable Integer id, @Valid @RequestBody WarehouseRequest request) {
        return ResponseEntity.ok(warehouseService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        warehouseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{warehouseId}/products")
    public ResponseEntity<List<ProductWarehouseResponse>> getProducts(@PathVariable Integer warehouseId) {
        return ResponseEntity.ok(warehouseService.getWarehouseProducts(warehouseId));
    }

    @GetMapping("/{warehouseId}/users")
    public ResponseEntity<List<UserResponse>> getUsers(@PathVariable Integer warehouseId) {
        return ResponseEntity.ok(warehouseService.getWarehouseUsers(warehouseId));
    }

    @PostMapping("/{warehouseId}/users")
    public ResponseEntity<Void> assignUser(@PathVariable Integer warehouseId, @Valid @RequestBody AssignUserWarehouseRequest request) {
        warehouseService.assignUser(warehouseId, request.userId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{warehouseId}/users/{userId}")
    public ResponseEntity<Void> removeUser(@PathVariable Integer warehouseId, @PathVariable Integer userId) {
        warehouseService.removeUser(warehouseId, userId);
        return ResponseEntity.noContent().build();
    }
}
