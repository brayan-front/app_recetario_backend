package com.example.recetario.repository;

import com.example.recetario.model.Preparation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PreparationRepository extends JpaRepository<Preparation, UUID> {}
