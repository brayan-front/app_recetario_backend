package com.recetario.backend.services;

import com.recetario.backend.dto.MenuItemRequest;
import com.recetario.backend.dto.MenuItemResponse;
import com.recetario.backend.entities.Menu;
import com.recetario.backend.entities.MenuItem;
import com.recetario.backend.repositories.MenuItemRepository;
import com.recetario.backend.repositories.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MenuItemService {

    private final MenuRepository menuRepo;
    private final MenuItemRepository repo;

    public MenuItemService(MenuRepository menuRepo, MenuItemRepository repo) {
        this.menuRepo = menuRepo;
        this.repo = repo;
    }

    public MenuItemResponse addItem(UUID restaurantId, UUID menuId, MenuItemRequest req) {

        Menu menu = menuRepo.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        if (!menu.getRestaurantId().equals(restaurantId))
            throw new RuntimeException("Menu does not belong to restaurant");

        MenuItem item = new MenuItem();
        item.setMenuItemId(UUID.randomUUID());
        item.setMenuId(menuId);
        item.setRecipeId(UUID.fromString(req.getRecipeId()));

        return toResponse(repo.save(item));
    }

    public List<MenuItemResponse> listItems(UUID restaurantId, UUID menuId) {
        return repo.findByMenuId(menuId)
                .stream().map(this::toResponse).toList();
    }

    public void deleteItem(UUID restaurantId, UUID menuId, UUID itemId) {
        repo.deleteById(itemId);
    }

    private MenuItemResponse toResponse(MenuItem m) {
        return new MenuItemResponse(
                m.getMenuItemId(),
                m.getMenuId(),
                m.getRecipeId()
        );
    }
}
