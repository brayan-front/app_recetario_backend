package com.recetario.backend.repositories;

import com.recetario.backend.entities.MenuPlanningItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuPlanningItemRepository extends JpaRepository<MenuPlanningItem, UUID> {
    List<MenuPlanningItem> findByPlanningId(UUID planningId);
}
