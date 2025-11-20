package com.recetario.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeRequest {
    private String title;
    private String description;
    private Integer servings;
    private String authorId;   // viene en string pero se convierte a UUID
}
