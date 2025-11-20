package com.recetario.backend.repositories;

import com.recetario.backend.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID> {
    List<Menu> findByRestaurantId(UUID restaurantId);
}
