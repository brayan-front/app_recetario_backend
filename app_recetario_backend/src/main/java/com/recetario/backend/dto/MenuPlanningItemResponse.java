package com.recetario.backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuPlanningItemResponse {

    private UUID planningItemId;
    private UUID planningId;
    private UUID recipeId;
    private Integer servings;

    private String recipeTitle;
    private String recipeDescription;

    // Constructor que tu servicio está usando (4 parámetros)
    public MenuPlanningItemResponse(UUID planningItemId, UUID planningId, UUID recipeId, Integer servings) {
        this.planningItemId = planningItemId;
        this.planningId = planningId;
        this.recipeId = recipeId;
        this.servings = servings;

        // Campos opcionales quedan null
        this.recipeTitle = null;
        this.recipeDescription = null;
    }
}
