package com.recetario.backend.controllers;

import com.recetario.backend.entities.Restaurant;
import com.recetario.backend.services.RestaurantService;

import com.recetario.backend.dto.RecipeRequest;
import com.recetario.backend.dto.RecipeResponse;
import com.recetario.backend.services.RecipeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService service;
    private final RecipeService recipeService;

    public RestaurantController(RestaurantService service, RecipeService recipeService) {
        this.service = service;
        this.recipeService = recipeService;
    }

    // ----------------------
    // RESTAURANTS CRUD
    // ----------------------

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getById(@PathVariable UUID id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Restaurant create(@RequestBody Restaurant restaurant) {
        return service.create(restaurant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable UUID id, @RequestBody Restaurant updated) {
        return service.update(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // ----------------------
    // RECIPES BY RESTAURANT
    // ----------------------

    // Crear receta en un restaurante
    @PostMapping("/{restaurantId}/recipes")
    public ResponseEntity<RecipeResponse> createRecipeForRestaurant(
            @PathVariable UUID restaurantId,
            @RequestBody RecipeRequest request) {

        return ResponseEntity.ok(recipeService.createForRestaurant(restaurantId, request));
    }

    // Listar recetas del restaurante
    @GetMapping("/{restaurantId}/recipes")
    public ResponseEntity<List<RecipeResponse>> getRecipesByRestaurant(
            @PathVariable UUID restaurantId) {

        return ResponseEntity.ok(recipeService.findByRestaurant(restaurantId));
    }

    // Obtener una receta específica de ese restaurante
    @GetMapping("/{restaurantId}/recipes/{recipeId}")
    public ResponseEntity<RecipeResponse> getRecipe(
            @PathVariable UUID restaurantId,
            @PathVariable UUID recipeId) {

        return ResponseEntity.ok(recipeService.findByRestaurantAndId(restaurantId, recipeId));
    }

    // Actualizar receta de restaurante
    @PutMapping("/{restaurantId}/recipes/{recipeId}")
    public ResponseEntity<RecipeResponse> updateRecipe(
            @PathVariable UUID restaurantId,
            @PathVariable UUID recipeId,
            @RequestBody RecipeRequest request) {

        return ResponseEntity.ok(recipeService.updateInRestaurant(
                restaurantId, recipeId, request
        ));
    }

    // Eliminar receta de restaurante
    @DeleteMapping("/{restaurantId}/recipes/{recipeId}")
    public ResponseEntity<Void> deleteRecipe(
            @PathVariable UUID restaurantId,
            @PathVariable UUID recipeId) {

        recipeService.deleteInRestaurant(restaurantId, recipeId);
        return ResponseEntity.noContent().build();
    }

}

