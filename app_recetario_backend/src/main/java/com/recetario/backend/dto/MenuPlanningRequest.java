package com.recetario.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MenuPlanningRequest {
    private String menuId;
    private LocalDate plannedDate;
}
