package com.recetario.backend.services;

import com.recetario.backend.dto.RecipeRequest;
import com.recetario.backend.dto.RecipeResponse;
import com.recetario.backend.entities.Recipe;
import com.recetario.backend.repositories.RecipeRepository;

import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // Crear receta asociada a restaurante
    public RecipeResponse createForRestaurant(UUID restaurantId, RecipeRequest request) {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(UUID.randomUUID());
        recipe.setRestaurantId(restaurantId);
        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setServings(request.getServings());
        recipe.setAuthorId(UUID.fromString(request.getAuthorId()));
        recipe.setCreatedAt(OffsetDateTime.now());
        return toResponse(recipeRepository.save(recipe));
    }

    // Listar recetas por restaurante
    public List<RecipeResponse> findByRestaurant(UUID restaurantId) {
        return recipeRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // Obtener receta validando restaurante
    public RecipeResponse findByRestaurantAndId(UUID restaurantId, UUID recipeId) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        if (!recipe.getRestaurantId().equals(restaurantId)) {
            throw new RuntimeException("Recipe does not belong to this restaurant");
        }

        return toResponse(recipe);
    }

    // Actualizar receta dentro del restaurante
    public RecipeResponse updateInRestaurant(UUID restaurantId, UUID recipeId, RecipeRequest request) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        if (!recipe.getRestaurantId().equals(restaurantId)) {
            throw new RuntimeException("Recipe does not belong to this restaurant");
        }

        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setServings(request.getServings());

        return toResponse(recipeRepository.save(recipe));
    }

    // Eliminar receta
    public void deleteInRestaurant(UUID restaurantId, UUID recipeId) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        if (!recipe.getRestaurantId().equals(restaurantId)) {
            throw new RuntimeException("Recipe does not belong to this restaurant");
        }

        recipeRepository.delete(recipe);
    }

     private RecipeResponse toResponse(Recipe r) {
        return new RecipeResponse(
                r.getRecipeId(),
                r.getRestaurantId(),
                r.getAuthorId(),
                r.getTitle(),
                r.getDescription(),
                r.getServings(),
                r.getCreatedAt()
        );
    }




}
