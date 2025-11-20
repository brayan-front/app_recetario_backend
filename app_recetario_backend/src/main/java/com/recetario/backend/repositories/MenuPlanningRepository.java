package com.recetario.backend.repositories;

import com.recetario.backend.entities.MenuPlanning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuPlanningRepository extends JpaRepository<MenuPlanning, UUID> {
    List<MenuPlanning> findByRestaurantId(UUID restaurantId);
}
