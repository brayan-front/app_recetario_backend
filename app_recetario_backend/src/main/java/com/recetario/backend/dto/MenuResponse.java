package com.recetario.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class MenuResponse {
    private UUID menuId;
    private UUID restaurantId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}
