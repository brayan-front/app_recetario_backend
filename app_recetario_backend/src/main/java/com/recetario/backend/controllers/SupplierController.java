package com.recetario.backend.controllers;

import com.recetario.backend.dto.SupplierRequest;
import com.recetario.backend.entities.Supplier;
import com.recetario.backend.services.SupplierService;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/suppliers")

public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // CREATE
    @PostMapping
    public Supplier createSupplier(@Valid @RequestBody SupplierRequest request) {
        return supplierService.createSupplier(request);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable UUID  id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(
            @PathVariable UUID  id,
            @RequestBody Supplier supplierDetails) {

        return ResponseEntity.ok(supplierService.update(id, supplierDetails));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable UUID  id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

}
