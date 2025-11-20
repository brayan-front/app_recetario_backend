package com.recetario.backend.services;

import com.recetario.backend.entities.Role;
import com.recetario.backend.entities.User;
import com.recetario.backend.entities.RestaurantUser;
import com.recetario.backend.repositories.RoleRepository;
import com.recetario.backend.repositories.UserRepository;
import com.recetario.backend.repositories.RestaurantUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RestaurantUserRepository restaurantUserRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // =============================
    // REGISTRO DE USUARIO
    // =============================
    @Transactional
    public User registerUser(String username, String email, String rawPassword) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("El correo ya está en uso");
        }
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        User user = new User();
        user.setUserId(UUID.randomUUID());
        user.setUsername(username);
        user.setEmail(email);
    user.setPassword(passwordEncoder.encode(rawPassword));
        user.setActive(true);
        userRepository.save(user);

        return user;
    }

    // =============================
    // LOGIN DE USUARIO
    // =============================
    public User login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return user;
    }

    // =============================
    // LISTAR USUARIOS
    // =============================
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // =============================
    // OBTENER USUARIO POR ID
    // =============================
    public User getUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // =============================
    // ACTUALIZAR USUARIO
    // =============================
    @Transactional
    public User updateUser(UUID userId, String fullName, String email, String rawPassword) {

        User user = getUserById(userId);

        if (fullName != null && !fullName.isEmpty()) {
            user.setFullName(fullName);
        }

        if (email != null && !email.isEmpty()) {
            // Validar que no exista otro usuario con ese correo
            if (userRepository.existsByEmail(email) && !email.equals(user.getEmail())) {
                throw new RuntimeException("El correo ya está en uso");
            }
            user.setEmail(email);
        }

        if (rawPassword != null && !rawPassword.isEmpty()) {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }

        return userRepository.save(user);
    }
    
    // =============================
    // ASIGNAR ROL A UN USUARIO
    // =============================
    @Transactional
    public void assignRoleToUser(UUID userId, UUID roleId, UUID restaurantId) {
        User user = getUserById(userId);
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        RestaurantUser restaurantUser = new RestaurantUser();
        restaurantUser.setUser(user);
        restaurantUser.setRole(role);
        restaurantUser.setRestaurantId(restaurantId);

        restaurantUserRepository.save(restaurantUser);
    }
}
