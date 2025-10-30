package com.example.recetario.repository;

import com.example.recetario.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface RecipeRepository extends JpaRepository<Recipe, UUID> {}
