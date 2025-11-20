package com.recetario.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @Column(name = "menu_item_id")
    private UUID menuItemId;

    @Column(name = "menu_id", nullable = false)
    private UUID menuId;

    @Column(name = "recipe_id", nullable = false)
    private UUID recipeId;
}
