package com.example.recetario.repository;

import com.example.recetario.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {}
