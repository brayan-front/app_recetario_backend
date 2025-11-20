package com.recetario.backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuPlanningResponse {

    private UUID planningId;
    private UUID restaurantId;
    private UUID menuId;
    private LocalDate plannedDate;

    private String menuName;

    // Constructor que tu servicio necesita (4 parámetros)
    public MenuPlanningResponse(UUID planningId, UUID restaurantId, UUID menuId, LocalDate plannedDate) {
        this.planningId = planningId;
        this.restaurantId = restaurantId;
        this.menuId = menuId;
        this.plannedDate = plannedDate;

        // campo opcional queda null
        this.menuName = null;
    }
}
