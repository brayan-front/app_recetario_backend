// package com.proyecto.code.menus.model;
package com.recetario.backend.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class menu {
    private int id;
    private String nombre;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> recipeIds = new ArrayList<>();
    private List<Integer> menuIds;

    public menu(int id, String nombre, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.nombre = nombre;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.nombre = name;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<String> getRecipeIds() { return recipeIds; }
    public void setRecipeIds(List<String> recipeIds) { this.recipeIds = recipeIds; }

    public void addRecipe(String recipeId) {
        this.recipeIds.add(recipeId);
    }
}
