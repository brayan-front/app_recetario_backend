package com.recetario.backend.services;

import com.recetario.backend.dto.MenuPlanningRequest;
import com.recetario.backend.dto.MenuPlanningResponse;
import com.recetario.backend.entities.MenuPlanning;
import com.recetario.backend.repositories.MenuPlanningRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MenuPlanningService {

    private final MenuPlanningRepository repo;

    public MenuPlanningService(MenuPlanningRepository repo) {
        this.repo = repo;
    }

    public MenuPlanningResponse create(UUID restaurantId, MenuPlanningRequest req) {

        MenuPlanning p = new MenuPlanning();
        p.setPlanningId(UUID.randomUUID());
        p.setRestaurantId(restaurantId);
        p.setMenuId(UUID.fromString(req.getMenuId()));
        p.setPlannedDate(req.getPlannedDate());

        return toResponse(repo.save(p));
    }

    public List<MenuPlanningResponse> list(UUID restaurantId) {
        return repo.findByRestaurantId(restaurantId)
                .stream().map(this::toResponse).toList();
    }

    public void delete(UUID restaurantId, UUID planningId) {
        repo.deleteById(planningId);
    }

    private MenuPlanningResponse toResponse(MenuPlanning p) {
        return new MenuPlanningResponse(
                p.getPlanningId(),
                p.getRestaurantId(),
                p.getMenuId(),
                p.getPlannedDate()
        );
    }
}
