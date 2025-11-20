package com.recetario.backend.controllers;

import com.recetario.backend.dto.*;
import com.recetario.backend.entities.Restaurant;
import com.recetario.backend.services.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RecipeService recipeService;
    private final MenuService menuService;
    private final MenuItemService menuItemService;
    private final MenuPlanningService menuPlanningService;
    private final MenuPlanningItemService menuPlanningItemService;

    public RestaurantController(
            RestaurantService restaurantService,
            RecipeService recipeService,
            MenuService menuService,
            MenuItemService menuItemService,
            MenuPlanningService menuPlanningService,
            MenuPlanningItemService menuPlanningItemService
    ) {
        this.restaurantService = restaurantService;
        this.recipeService = recipeService;
        this.menuService = menuService;
        this.menuItemService = menuItemService;
        this.menuPlanningService = menuPlanningService;
        this.menuPlanningItemService = menuPlanningItemService;
    }

    // ----------------------
    // RESTAURANTS CRUD
    // ----------------------

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getById(@PathVariable UUID id) {
        return restaurantService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Restaurant create(@RequestBody Restaurant restaurant) {
        return restaurantService.create(restaurant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable UUID id, @RequestBody Restaurant updated) {
        return restaurantService.update(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return restaurantService.delete(id)
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
    
    // ----------------------
    // MENÚS 
    // ----------------------

 @PostMapping("/{restaurantId}/menus")
    public ResponseEntity<MenuResponse> createMenu(
            @PathVariable UUID restaurantId,
            @RequestBody MenuRequest request
    ) {
        return ResponseEntity.ok(menuService.create(restaurantId, request));
    }

    @GetMapping("/{restaurantId}/menus")
    public ResponseEntity<List<MenuResponse>> getMenus(
            @PathVariable UUID restaurantId
    ) {
        return ResponseEntity.ok(menuService.getByRestaurant(restaurantId));
    }

    @GetMapping("/{restaurantId}/menus/{menuId}")
    public ResponseEntity<MenuResponse> getMenu(
            @PathVariable UUID restaurantId,
            @PathVariable UUID menuId
    ) {
        return ResponseEntity.ok(menuService.getById(restaurantId, menuId));
    }

    @DeleteMapping("/{restaurantId}/menus/{menuId}")
    public ResponseEntity<Void> deleteMenu(
            @PathVariable UUID restaurantId,
            @PathVariable UUID menuId
    ) {
        menuService.delete(restaurantId, menuId);
        return ResponseEntity.noContent().build();
    }


    // ----------------------
    // MENU ITEMS
    // ----------------------

    @PostMapping("/{restaurantId}/menus/{menuId}/items")
    public ResponseEntity<MenuItemResponse> addMenuItem(
            @PathVariable UUID restaurantId,
            @PathVariable UUID menuId,
            @RequestBody MenuItemRequest request
    ) {
        return ResponseEntity.ok(
                menuItemService.addItem(restaurantId, menuId, request)
        );
    }

    @GetMapping("/{restaurantId}/menus/{menuId}/items")
    public ResponseEntity<List<MenuItemResponse>> listMenuItems(
            @PathVariable UUID restaurantId,
            @PathVariable UUID menuId
    ) {
        return ResponseEntity.ok(
                menuItemService.listItems(restaurantId, menuId)
        );
    }

    @DeleteMapping("/{restaurantId}/menus/{menuId}/items/{itemId}")
    public ResponseEntity<Void> deleteMenuItem(
            @PathVariable UUID restaurantId,
            @PathVariable UUID menuId,
            @PathVariable UUID itemId
    ) {
        menuItemService.deleteItem(restaurantId, menuId, itemId);
        return ResponseEntity.noContent().build();
    }


    // ----------------------
    // MENU PLANNING
    // ----------------------

    @PostMapping("/{restaurantId}/menu-planning")
    public ResponseEntity<MenuPlanningResponse> createPlanning(
            @PathVariable UUID restaurantId,
            @RequestBody MenuPlanningRequest request
    ) {
        return ResponseEntity.ok(
                menuPlanningService.create(restaurantId, request)
        );
    }

    @GetMapping("/{restaurantId}/menu-planning")
    public ResponseEntity<List<MenuPlanningResponse>> listPlanning(
            @PathVariable UUID restaurantId
    ) {
        return ResponseEntity.ok(
                menuPlanningService.list(restaurantId)
        );
    }

    @DeleteMapping("/{restaurantId}/menu-planning/{planningId}")
    public ResponseEntity<Void> deletePlanning(
            @PathVariable UUID restaurantId,
            @PathVariable UUID planningId
    ) {
        menuPlanningService.delete(restaurantId, planningId);
        return ResponseEntity.noContent().build();
    }


    // ----------------------
    // MENU PLANNING ITEMS
    // ----------------------

    @PostMapping("/{restaurantId}/menu-planning/{planningId}/items")
    public ResponseEntity<MenuPlanningItemResponse> addPlanningItem(
            @PathVariable UUID restaurantId,
            @PathVariable UUID planningId,
            @RequestBody MenuPlanningItemRequest request
    ) {
        return ResponseEntity.ok(
                menuPlanningItemService.addItem(planningId, request)
        );
    }

    @GetMapping("/{restaurantId}/menu-planning/{planningId}/items")
    public ResponseEntity<List<MenuPlanningItemResponse>> listPlanningItems(
            @PathVariable UUID restaurantId,
            @PathVariable UUID planningId
    ) {
        return ResponseEntity.ok(
                menuPlanningItemService.list(planningId)
        );
    }


}

