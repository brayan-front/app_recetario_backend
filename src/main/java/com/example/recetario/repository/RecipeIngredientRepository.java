package com.example.recetario.repository;

import com.example.recetario.model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;



public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, UUID> {}
