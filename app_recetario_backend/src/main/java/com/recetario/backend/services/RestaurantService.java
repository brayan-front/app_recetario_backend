//package com.recetario.restaurant.service;
package com.recetario.backend.services;

//import com.recetario.restaurant.model.Restaurant;
//import com.recetario.restaurant.repository.RestaurantRepository;
import com.recetario.backend.entities.Restaurant;
import com.recetario.backend.repositories.RestaurantRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getById(Long id) {
        return restaurantRepository.findById(id);
    }

    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Optional<Restaurant> update(Long id, Restaurant updated) {
        return restaurantRepository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setAddress(updated.getAddress());
            existing.setContact(updated.getContact());
            existing.setCity(updated.getCity());
            existing.setCountry(updated.getCountry());
            return restaurantRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

