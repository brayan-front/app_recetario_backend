package com.recetario.backend.controllers;

import com.recetario.backend.entities.User;
import com.recetario.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permite peticiones desde el frontend
public class UserController {

    private final UserService userService;

    // =============================
    // LISTAR TODOS LOS USUARIOS
    // =============================
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // =============================
    // OBTENER USUARIO POR ID
    // =============================
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    // =============================
    // ACTUALIZAR USUARIO
    // =============================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody Map<String, String> request) {
        try {
            String fullName = request.get("full_name");
            User updatedUser = userService.updateUser(id, fullName);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    // =============================
    // ASIGNAR ROL A UN USUARIO
    // =============================
    @PostMapping("/{id}/assign-role")
    public ResponseEntity<?> assignRoleToUser(
        @PathVariable UUID id,
        @RequestBody Map<String, String> request
    ) {
        try {
            UUID roleId = UUID.fromString(request.get("role_id"));
            UUID restaurantId = UUID.fromString(request.get("restaurant_id"));
            userService.assignRoleToUser(id, roleId, restaurantId);
            return ResponseEntity.ok(Map.of("message", "Rol asignado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
