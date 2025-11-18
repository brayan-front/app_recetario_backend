//package com.example.recetario.service;
package com.recetario.backend.services;

//import com.example.recetario.model.*;
//import com.example.recetario.repository.*;
//import com.example.recetario.dto.RecipeRequest;
import com.recetario.backend.entities.*;
import com.recetario.backend.repositories.*;
import com.recetario.backend.dto.RecipeRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import lombok.*;
import java.util.UUID;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepo;
    private final IngredientRepository ingredientRepo;
    private final UnitRepository unitRepo;

    public RecipeService(RecipeRepository recipeRepo, IngredientRepository ingredientRepo, UnitRepository unitRepo) {
        this.recipeRepo = recipeRepo;
        this.ingredientRepo = ingredientRepo;
        this.unitRepo = unitRepo;
    }

    public List<Recipe> findAll() { return recipeRepo.findAll(); }
    public Optional<Recipe> findById(UUID id) { return recipeRepo.findById(id); }

    @Transactional
    public Recipe create(RecipeRequest req) {
        Recipe r = Recipe.builder()
                .restaurantId(req.restaurantId)
                .title(req.title)
                .description(req.description)
                .servings(req.servings)
                .build();

        if (req.ingredients != null) {
            for (var it : req.ingredients) {
                Ingredient ing = it.ingredientId!=null? ingredientRepo.findById(it.ingredientId).orElse(null) : null;
                Unit u = it.unitId!=null? unitRepo.findById(it.unitId).orElse(null) : null;
                RecipeIngredient ri = RecipeIngredient.builder()
                        .recipe(r)
                        .ingredient(ing)
                        .quantity(it.quantity)
                        .unit(u)
                        .build();
                r.getIngredients().add(ri);
            }
        }
        if (req.preparations != null) {
            for (var p : req.preparations) {
                Preparation prep = Preparation.builder()
                        .recipe(r)
                        .stepNumber(p.stepNumber)
                        .instructions(p.instructions)
                        .build();
                r.getPreparations().add(prep);
            }
        }
        return recipeRepo.save(r);
    }

    @Transactional
    public Recipe update(UUID id, RecipeRequest req) {
        Recipe r = recipeRepo.findById(id).orElseThrow();
        if (req.description != null) r.setDescription(req.description);
        if (req.title != null) r.setTitle(req.title);
        if (req.servings != null) r.setServings(req.servings);
        r.getIngredients().clear(); r.getPreparations().clear();
        if (req.ingredients != null) {
            for (var it : req.ingredients) {
                Ingredient ing = it.ingredientId!=null? ingredientRepo.findById(it.ingredientId).orElse(null) : null;
                Unit u = it.unitId!=null? unitRepo.findById(it.unitId).orElse(null) : null;
                RecipeIngredient ri = RecipeIngredient.builder().recipe(r).ingredient(ing).quantity(it.quantity).unit(u).build();
                r.getIngredients().add(ri);
            }
        }
        if (req.preparations != null) {
            for (var p : req.preparations) {
                Preparation prep = Preparation.builder().recipe(r).stepNumber(p.stepNumber).instructions(p.instructions).build();
                r.getPreparations().add(prep);
            }
        }
        return recipeRepo.save(r);
    }

    public void delete(UUID id) { recipeRepo.deleteById(id); }
}
