package com.recetario.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RecipeResponse {

    private UUID recipeId;
    private UUID restaurantId;
    private UUID authorId;
    private String title;
    private String description;
    private Integer servings;
    private OffsetDateTime createdAt;
}
