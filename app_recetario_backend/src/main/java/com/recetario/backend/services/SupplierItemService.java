package com.recetario.backend.services;

import com.recetario.backend.entities.SupplierItem;
import com.recetario.backend.repositories.SupplierItemRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SupplierItemService {

    private final SupplierItemRepository repository;

    public SupplierItemService(SupplierItemRepository repository) {
        this.repository = repository;
    }

    public SupplierItem createItem(UUID supplierId, SupplierItem request) {

        SupplierItem item = new SupplierItem();
        item.setSupplierItemId(UUID.randomUUID());
        item.setSupplierId(supplierId);
        item.setIngredientId(request.getIngredientId());
        item.setPricePerUnit(request.getPricePerUnit());
        item.setUnitId(request.getUnitId());

        return repository.save(item);
    }
}
