package com.recetario.backend.repositories;

import com.recetario.backend.entities.SupplierItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SupplierItemRepository extends JpaRepository<SupplierItem, UUID> {

    List<SupplierItem> findBySupplierId(UUID supplierId);

}
