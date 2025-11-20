package com.recetario.backend.services;

import com.recetario.backend.dto.MenuPlanningItemRequest;
import com.recetario.backend.dto.MenuPlanningItemResponse;
import com.recetario.backend.entities.MenuPlanningItem;
import com.recetario.backend.repositories.MenuPlanningItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MenuPlanningItemService {

    private final MenuPlanningItemRepository repo;

    public MenuPlanningItemService(MenuPlanningItemRepository repo) {
        this.repo = repo;
    }

    public MenuPlanningItemResponse addItem(UUID planningId, MenuPlanningItemRequest req) {

        MenuPlanningItem m = new MenuPlanningItem();
        m.setPlanningItemId(UUID.randomUUID());
        m.setPlanningId(planningId);
        m.setRecipeId(UUID.fromString(req.getRecipeId()));
        m.setServings(req.getServings());

        return toResponse(repo.save(m));
    }

    public List<MenuPlanningItemResponse> list(UUID planningId) {
        return repo.findByPlanningId(planningId)
                .stream().map(this::toResponse).toList();
    }

    private MenuPlanningItemResponse toResponse(MenuPlanningItem p) {
        return new MenuPlanningItemResponse(
                p.getPlanningItemId(),
                p.getPlanningId(),
                p.getRecipeId(),
                p.getServings()
        );
    }
}
