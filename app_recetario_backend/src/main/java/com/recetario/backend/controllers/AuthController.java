package com.recetario.backend.controllers;

import com.recetario.backend.entities.User;
import com.recetario.backend.services.UserService;
import com.recetario.backend.security.JwtUtil;
import com.recetario.backend.repositories.RestaurantUserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permite peticiones desde el frontend
public class AuthController {

    private final UserService userService;
    private final RestaurantUserRepository restaurantUserRepository;
    private final JwtUtil jwtUtil;

    // =============================
    // REGISTRO DE USUARIO
    // =============================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String email = request.get("email");
        String password = request.get("password");

        User newUser = userService.registerUser(username, email, password);

        return ResponseEntity.ok(Map.of(
                "message", "Usuario registrado correctamente",
                "userId", newUser.getUserId(),
                "email", newUser.getEmail(),
                "username", newUser.getUsername()
        ));
    }

    // =============================
    // LOGIN DE USUARIO
    // =============================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        final String email = request.get("email");
        final String password = request.get("password");
        final User user = userService.login(email, password);
        final String token = jwtUtil.generateToken(user.getEmail());

        // Buscar el rol del usuario
        String roleId = null;
        String roleName = null;

        var assignments = restaurantUserRepository.findByUser_UserId(user.getUserId());

        if (!assignments.isEmpty()) {
            var ru = assignments.get(0);
            if (ru.getRole() != null) {
                roleId = ru.getRole().getRoleId().toString();
                roleName = ru.getRole().getName();
            }
        }

        // Usar un HashMap porque permite null
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Inicio de sesión exitoso");
        response.put("userId", user.getUserId());
        response.put("email", user.getEmail());
        response.put("username", user.getUsername());
        response.put("token", token);
        response.put("roleId", roleId);   // puede venir null sin romper
        response.put("roleName", roleName);

        return ResponseEntity.ok(response);
    }



    //@PostMapping("/login")
    ///public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
    //    String email = request.get("email");
    //    String password = request.get("password");

    //    User user = userService.login(email, password);
    //    String token = jwtUtil.generateToken(user.getEmail());

    //    return ResponseEntity.ok(Map.of(
    //            "message", "Inicio de sesión exitoso",
    //            "userId", user.getUserId(),
    //            "email", user.getEmail(),
    //            "username", user.getUsername(),
    //            "token", token
    //    ));
    //}


    // =============================
    // OBTENER DATOS DE USUARIO POR ID
    // =============================
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
