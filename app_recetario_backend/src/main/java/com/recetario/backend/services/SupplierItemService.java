package com.recetario.backend.services;

import com.recetario.backend.entities.SupplierItem;
import com.recetario.backend.repositories.SupplierItemRepository;
import com.recetario.backend.repositories.IngredientRepository;
import com.recetario.backend.repositories.SupplierRepository;
import com.recetario.backend.repositories.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class SupplierItemService {

    private final SupplierItemRepository repository;
    private final SupplierRepository supplierRepository;
    private final IngredientRepository ingredientRepository;
    private final UnitRepository unitRepository;

    public SupplierItemService(
            SupplierItemRepository repository,
            SupplierRepository supplierRepository,
            IngredientRepository ingredientRepository,
            UnitRepository unitRepository
    ) {
        this.repository = repository;
        this.supplierRepository = supplierRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitRepository = unitRepository;
    }

    // CREATE ITEMS
    public SupplierItem createItem(UUID supplierId, SupplierItem request) {

        SupplierItem item = new SupplierItem();
        item.setSupplierItemId(UUID.randomUUID());
        item.setSupplierId(supplierId);
        item.setIngredientId(request.getIngredientId());
        item.setPricePerUnit(request.getPricePerUnit());
        item.setUnitId(request.getUnitId());

        return repository.save(item);
    }

    // LISTAR ITEMS
    public List<Map<String, Object>> getItemsBySupplier(UUID supplierId) {

        var supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        var items = repository.findBySupplierId(supplierId);

        return items.stream().map(item -> {

            var ingredient = ingredientRepository.findById(item.getIngredientId())
                    .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));

            var unit = unitRepository.findById(item.getUnitId())
                    .orElseThrow(() -> new RuntimeException("Unidad no encontrada"));

            return Map.<String, Object>of(
                    "supplier_id", supplierId,
                    "supplier_name", supplier.getName(),
                    "ingredient_id", item.getIngredientId(),
                    "ingredient_name", ingredient.getName(),
                    "ingredient_description", ingredient.getDescription(),
                    "unit_id", unit.getId(),
                    "unit_code", unit.getCode(),
                    "price_per_unit", item.getPricePerUnit()
            );

        }).toList();
    }


}
