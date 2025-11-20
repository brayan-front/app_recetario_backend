package com.recetario.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "menu_planning")
public class MenuPlanning {

    @Id
    @Column(name = "planning_id")
    private UUID planningId;

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;

    @Column(name = "menu_id", nullable = false)
    private UUID menuId;

    @Column(name = "planned_date", nullable = false)
    private LocalDate plannedDate;
}
