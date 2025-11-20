package com.recetario.backend.services;

import com.recetario.backend.dto.SupplierRequest;
import com.recetario.backend.entities.Supplier;
import com.recetario.backend.repositories.SupplierRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // CREATE
    public Supplier createSupplier(SupplierRequest request) {

        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setContactInfo(request.getContactInfo());

        return supplierRepository.save(supplier);
    }

    // GET ALL
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    //GET BY ID
    public Supplier getSupplierById(UUID  id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    // UPDATE
    public Supplier update(UUID id, Supplier details) {
    Supplier existing = supplierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Supplier not found"));

    existing.setName(details.getName());
    existing.setContactInfo(details.getContactInfo());

    return supplierRepository.save(existing);
}

    //DELETE
    public void deleteSupplier(UUID  id) {
        Supplier supplier = getSupplierById(id);
        supplierRepository.delete(supplier);
    }

}
