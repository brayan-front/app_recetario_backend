package com.recetario.backend.controllers;

import com.recetario.backend.entities.Role;
import com.recetario.backend.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permite peticiones desde el frontend
public class RoleController {

    private final RoleRepository roleRepository;

    // =============================
    // CREAR UN NUEVO ROL
    // =============================
    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody Map<String, String> request) {
        String name = request.get("name");

        // Validar que no exista un rol con ese nombre
        Optional<Role> existingRole = roleRepository.findByName(name);
        if (existingRole.isPresent()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "El rol ya existe"
            ));
        }

        Role role = Role.builder()
                .name(name)
                .build();

        roleRepository.save(role);

        return ResponseEntity.ok(Map.of(
                "message", "Rol creado correctamente",
                "roleId", role.getRoleId(),
                "name", role.getName()
        ));
    }

    // =============================
    // LISTAR TODOS LOS ROLES
    // =============================
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return ResponseEntity.ok(roles);
    }

    // =============================
    // OBTENER UN ROL POR ID
    // =============================
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable UUID id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of(
                    "error", "Rol no encontrado"
            ));
        }
        return ResponseEntity.ok(role.get());
    }
}
