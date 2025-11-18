// package com.example.recetario.model;
package com.recetario.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Ingredient {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Calories per unit is required")
    @Min(value = 0, message = "Calories per unit must be non-negative")
    private Integer caloriesPerUnit;

    @ManyToOne
    private Unit defaultUnit;
}
