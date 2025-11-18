//package com.recetario.restaurant.repository;
package com.recetario.backend.repositories;

//import com.recetario.restaurant.model.Restaurant;
import com.recetario.backend.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {}
