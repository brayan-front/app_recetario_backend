package com.example.recetario.model;
import java.util.UUID;

import jakarta.persistence.*;


@Entity

// Esta clase representa una tabla en la base de datos llamada "units"
@Table(name = "units")

public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    
    private UUID id;
        // Este será un campo de texto donde se guarda el nombre de la unidad (ej: "gramos", "litros"
    private String name;

      // Constructor vacío (requerido por JPA)
    protected Unit() {
    }

     // Constructor para crear una unidad rápidamente desde código
    public Unit(String name) {
        this.name = name;
    }

     // Métodos getter y setter → sirven para acceder o modificar los datos del objeto
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
