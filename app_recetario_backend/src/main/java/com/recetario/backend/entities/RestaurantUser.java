package com.recetario.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "restaurant_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Relación con usuario
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relación con rol
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // Por ahora dejamos este campo, aunque tu módulo no gestiona restaurantes directamente
    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;
}
