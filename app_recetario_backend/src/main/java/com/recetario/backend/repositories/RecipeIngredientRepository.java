package com.recetario.backend.repositories;

import com.recetario.backend.entities.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, UUID> {}
