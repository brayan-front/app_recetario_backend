package com.recetario.backend.repositories;

import com.recetario.backend.entities.RestaurantUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantUserRepository extends JpaRepository<RestaurantUser, String> {

    /**
     * Busca todas las asignaciones de un usuario (user.userId es UUID).
     */
    List<RestaurantUser> findByUser_UserId(UUID userId);

    /**
     * Busca la asignación específica entre user.userId (UUID) y restaurantId (UUID).
     */
    Optional<RestaurantUser> findByUser_UserIdAndRestaurantId(UUID userId, UUID restaurantId);
}
