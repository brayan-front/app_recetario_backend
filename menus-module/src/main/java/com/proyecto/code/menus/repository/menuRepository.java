package com.proyecto.code.menus.repository;

import com.proyecto.code.menus.model.menu;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class menuRepository {

    private final List<menu> menus = new ArrayList<>();
    private final List<MenuPlanning> plannings = new ArrayList<>();

    public menuRepository() {
        // Datos iniciales (simulan base de datos)
        menus.add(new menu(001, "Menú semanal 1", LocalDate.of(2025, 10, 6), LocalDate.of(2025, 10, 13)));
        menus.add(new menu(002, "Menú vegetariano", LocalDate.of(2025, 10, 8), LocalDate.of(2025, 10, 15)));
        menus.add(new menu(003, "Menú gourmet", LocalDate.of(2025, 10, 10), LocalDate.of(2025, 10, 17)));

        // Planificación base
        plannings.add(new MenuPlanning(1, "Planificación semanal octubre",
                LocalDate.of(2025, 10, 6),
                LocalDate.of(2025, 10, 13),
                List.of(1, 2)));
    }

    public List<menu> findAll() {
        return menus;
    }

    public Optional<menu> findById(int id) {
        return menus.stream()
                .filter(m -> m.getId() == id)
                .findAny();
    }

    public menu save(menu menu) {
        menus.add(menu);
        return menu;
    }

    public boolean addRecipeToMenu(int menuId, String recipeId) {
        Optional<menu> opt = findById(menuId);
        if (opt.isPresent()) {
            opt.get().addRecipe(recipeId);
            return true;
        }
        return false;
    }

    public List<String> getRecipesFromMenu(int menuId) {
        return findById(menuId)
                .map(menu::getRecipeIds)
                .orElseGet(ArrayList::new);
    }

    // 🔹 Métodos de planificación (menu-planning)
    public List<MenuPlanning> getAllPlannings() {
        return plannings;
    }

    public Optional<MenuPlanning> getPlanningById(int id) {
        return plannings.stream().filter(p -> p.getId() == id).findFirst();
    }

    public MenuPlanning savePlanning(MenuPlanning planning) {
        plannings.add(planning);
        return planning;
    }

    // 👇 Clase interna (no necesitas otro archivo)
    public static class MenuPlanning {
        private int id;
        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
        private List<Integer> menuIds;

        public MenuPlanning() {}

        public MenuPlanning(int id, String name, LocalDate startDate, LocalDate endDate, List<Integer> menuIds) {
            this.id = id;
            this.name = name;
            this.startDate = startDate;
            this.endDate = endDate;
            this.menuIds = menuIds;
        }

        // Getters y setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public LocalDate getStartDate() { return startDate; }
        public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

        public LocalDate getEndDate() { return endDate; }
        public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

        public List<Integer> getMenuIds() { return menuIds; }
        public void setMenuIds(List<Integer> menuIds) { this.menuIds = menuIds; }
    }
}
