package com.recetario.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class MenuItemResponse {
    private UUID menuItemId;
    private UUID menuId;
    private UUID recipeId;
}
