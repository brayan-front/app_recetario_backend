package com.recetario.restaurant.service;

import com.recetario.restaurant.model.Restaurant;
import com.recetario.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant r) {
        return repository.save(r);
    }

    public List<Restaurant> listAll() {
        return repository.findAll();
    }

    public Optional<Restaurant> getById(Long id) {
        return repository.findById(id);
    }

    public Restaurant update(Long id, Restaurant updated) {
        return repository.findById(id).map(r -> {
            r.setName(updated.getName());
            r.setAddress(updated.getAddress());
            r.setContact(updated.getContact());
            return repository.save(r);
        }).orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
