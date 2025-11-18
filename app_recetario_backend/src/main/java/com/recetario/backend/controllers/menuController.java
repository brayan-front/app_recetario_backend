//package com.proyecto.code.menus.controller;
package com.recetario.backend.controllers;

//import com.proyecto.code.menus.model.menu;
//import com.proyecto.code.menus.repository.menuRepository;
//import com.proyecto.code.menus.repository.menuRepository.MenuPlanning;
import com.recetario.backend.entities.menu;
import com.recetario.backend.repositories.menuRepository;
import com.recetario.backend.repositories.menuRepository.MenuPlanning;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/menus")
public class menuController {

    private final menuRepository repository;

    public menuController(menuRepository repository) {
        this.repository = repository;
    }

    // GET /menus — listar todos los menús
    @GetMapping
    public List<menu> getAllMenus() {
        return repository.findAll();
    }

    // 🔹 2. Obtener un menú por ID
    @GetMapping("/{id}")
    public ResponseEntity<menu> getMenuById(@PathVariable int id) {
        Optional<menu> menuOpt = repository.findById(id);
        return menuOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<menu> createMenu(@RequestBody menu menu) {
        menu nuevo = repository.save(menu);
        return ResponseEntity.ok(nuevo);
    }

    // POST /menus/{id}/recipes
    @PostMapping("/{id}/recipes")
    public String addRecipeToMenu(@PathVariable int id, @RequestBody Map<String, String> body) {
        String recipeId = body.get("recipe_id");
        boolean added = repository.addRecipeToMenu(id, recipeId);
        if (added) {
            return "Receta agregada al menú con ID " + id;
        } else {
            return "No se encontró el menú con ID " + id;
        }
    }

    // GET /menus/{id}/recipes
    @GetMapping("/{id}/recipes")
    public List<String> getRecipesFromMenu(@PathVariable int id) {
        return repository.getRecipesFromMenu(id);
    }

    // ========== MENU PLANNING ==========
    @GetMapping("/planning")
    public List<MenuPlanning> getAllPlannings() {
        return repository.getAllPlannings();
    }

    @GetMapping("/planning/{id}")
    public Optional<MenuPlanning> getPlanningById(@PathVariable int id) {
        return repository.getPlanningById(id);
    }

    @PostMapping("/planning")
    public MenuPlanning createPlanning(@RequestBody MenuPlanning planning) {
        return repository.savePlanning(planning);
    }
}

