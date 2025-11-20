package com.recetario.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuPlanningItemRequest {
    private String recipeId;
    private Integer servings;
}
