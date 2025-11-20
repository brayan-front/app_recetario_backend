package com.recetario.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class SupplierRequest {

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    private String name;

    private String contactInfo;

    // Getters y Setters
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
