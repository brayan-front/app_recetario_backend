//package com.example.recetario.repository;
package com.recetario.backend.repositories;

//import com.example.recetario.model.Ingredient;
import com.recetario.backend.entities.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {}
