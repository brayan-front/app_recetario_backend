package com.recetario.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "supplier_items")
@Data
public class SupplierItem {

    @Id
    @Column(name = "supplier_item_id")
    private UUID supplierItemId;

    @Column(name = "supplier_id", nullable = false)
    private UUID supplierId;

    @Column(name = "ingredient_id", nullable = false)
    private UUID ingredientId;

    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;

    @Column(name = "unit_id", nullable = false)
    private UUID unitId;
}
