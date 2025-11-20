package com.recetario.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "menu_planning_items")
public class MenuPlanningItem {

    @Id
    @Column(name = "planning_item_id")
    private UUID planningItemId;

    @Column(name = "planning_id", nullable = false)
    private UUID planningId;

    @Column(name = "recipe_id", nullable = false)
    private UUID recipeId;

    @Column(nullable = false)
    private Integer servings;
}
