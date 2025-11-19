package com.recetario.backend.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue
    @Column(name = "supplier_id", updatable = false, nullable = false)
    private UUID supplierId;

    @Column(nullable = false)
    private String name;

    private String contactInfo;

    // Getters y Setters
    public UUID getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(UUID supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
