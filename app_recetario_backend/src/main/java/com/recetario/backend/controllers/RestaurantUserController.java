package com.recetario.backend.controllers;

import com.recetario.backend.dto.AssignUserRequest;
import com.recetario.backend.entities.RestaurantUser;
import com.recetario.backend.services.RestaurantUserService;
import com.recetario.backend.dto.RestaurantUserResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
public class RestaurantUserController {

    private final RestaurantUserService service;
    //private final RestaurantUserService restaurantUserService;

    public RestaurantUserController(RestaurantUserService service) {
        this.service = service;
    }

    @PostMapping("/{restaurantId}/assign-user")
    public ResponseEntity<RestaurantUser> assignUser(
            @PathVariable UUID restaurantId,
            @RequestBody AssignUserRequest request) {

        return ResponseEntity.ok(service.assignUser(restaurantId, request));
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<RestaurantUserResponse>> getUsersByRestaurant(
            @PathVariable("id") UUID restaurantId
    ) {
        List<RestaurantUserResponse> users = service.getUsersByRestaurant(restaurantId);
        return ResponseEntity.ok(users);
    }


}
