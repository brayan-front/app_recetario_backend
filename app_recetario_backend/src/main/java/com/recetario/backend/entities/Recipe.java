package com.recetario.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Recipe {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String restaurantId;
    private String title;
    @Column(length = 2000)
    private String description;
    private Integer servings;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Preparation> preparations = new ArrayList<>();}
