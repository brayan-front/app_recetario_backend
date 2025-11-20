package com.recetario.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "ingredients")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Ingredient {

    @Id
    @GeneratedValue
    @Column(name = "ingredient_id")
    private UUID ingredientId;

    @Column(unique = true)
    private String code;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @Column(name = "calories_per_unit")
    @NotNull(message = "Calories per unit is required")
    @Min(value = 0, message = "Calories per unit must be non-negative")
    private Integer caloriesPerUnit;

    @ManyToOne
    @JoinColumn(name = "default_unit_id")
    private Unit defaultUnit;

    @Column(name = "created_at")
    private java.time.OffsetDateTime createdAt;
}
