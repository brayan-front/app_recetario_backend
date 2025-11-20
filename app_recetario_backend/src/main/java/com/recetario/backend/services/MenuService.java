package com.recetario.backend.services;

import com.recetario.backend.dto.MenuRequest;
import com.recetario.backend.dto.MenuResponse;
import com.recetario.backend.entities.Menu;
import com.recetario.backend.repositories.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MenuService {

    private final MenuRepository repo;

    public MenuService(MenuRepository repo) {
        this.repo = repo;
    }

    public MenuResponse create(UUID restaurantId, MenuRequest req) {
        Menu m = new Menu();
        m.setMenuId(UUID.randomUUID());
        m.setRestaurantId(restaurantId);
        m.setName(req.getName());
        m.setStartDate(req.getStartDate());
        m.setEndDate(req.getEndDate());
        return toResponse(repo.save(m));
    }

    public List<MenuResponse> getByRestaurant(UUID restaurantId) {
        return repo.findByRestaurantId(restaurantId)
                .stream().map(this::toResponse).toList();
    }

    public MenuResponse getById(UUID restaurantId, UUID menuId) {
        Menu m = repo.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        if (!m.getRestaurantId().equals(restaurantId))
            throw new RuntimeException("Menu does not belong to this restaurant");
        return toResponse(m);
    }

    public void delete(UUID restaurantId, UUID menuId) {
        Menu m = repo.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        if (!m.getRestaurantId().equals(restaurantId))
            throw new RuntimeException("Menu does not belong to restaurant");
        repo.delete(m);
    }

    private MenuResponse toResponse(Menu m) {
        return new MenuResponse(
                m.getMenuId(),
                m.getRestaurantId(),
                m.getName(),
                m.getStartDate(),
                m.getEndDate()
        );
    }
}
