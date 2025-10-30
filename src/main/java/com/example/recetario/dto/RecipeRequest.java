package com.example.recetario.dto;

import lombok.*;
import java.util.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RecipeRequest {
    public String restaurantId;
    public String title;
    public String description;
    public Integer servings;
    public List<IngredientItem> ingredients;
    public List<PrepItem> preparations;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class IngredientItem {
        public UUID ingredientId;
        public Double quantity;
        public UUID unitId;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class PrepItem {
        public Integer stepNumber;
        public String instructions;
    }
}
