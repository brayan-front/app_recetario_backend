//package com.example.recetario.repository;
package com.recetario.backend.repositories;

//import com.example.recetario.model.Preparation;
import com.recetario.backend.entities.Preparation;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PreparationRepository extends JpaRepository<Preparation, UUID> {}
