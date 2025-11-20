package com.recetario.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RestaurantUserResponse {

    private UUID restaurantUserId;
    private UUID userId;
    private String userName;
    private String email;
    private UUID roleId;
    private String roleName;
    private OffsetDateTime joinedAt;
}
