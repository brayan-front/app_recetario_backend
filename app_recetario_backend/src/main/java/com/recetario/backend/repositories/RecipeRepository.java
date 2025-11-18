//package com.example.recetario.repository;
package com.recetario.backend.repositories;

//import com.example.recetario.model.Recipe;
import com.recetario.backend.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface RecipeRepository extends JpaRepository<Recipe, UUID> {}
