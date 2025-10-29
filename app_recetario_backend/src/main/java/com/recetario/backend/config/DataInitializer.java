package com.recetario.backend.config;

import com.recetario.backend.entities.Role;
import com.recetario.backend.entities.User;
import com.recetario.backend.repositories.RoleRepository;
import com.recetario.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args) {
        // =============================
        // ROLES POR DEFECTO
        // =============================
        createRoleIfNotExists("OWNER");
        createRoleIfNotExists("MANAGER");
        createRoleIfNotExists("CHEF");
        createRoleIfNotExists("WAITER");

        // =============================
        // USUARIO POR DEFECTO
        // =============================
        createDefaultUserIfNotExists();
    }

    /**
     * Crea un rol si no existe previamente en la base de datos.
     */
    private void createRoleIfNotExists(String roleName) {
        if (roleRepository.findByName(roleName).isEmpty()) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
            System.out.println("✅ Rol creado: " + roleName);
        }
    }

    /**
     * Crea un usuario por defecto si no existe.
     */
    private void createDefaultUserIfNotExists() {
        if (userRepository.findByEmail("felipe@gmail.com").isEmpty()) {
            User user = new User();
            user.setUserId(UUID.randomUUID());
            user.setUsername("felipe");
            user.setEmail("felipe@gmail.com");
            user.setPassword(passwordEncoder.encode("password"));
            user.setActive(true);
            userRepository.save(user);
            System.out.println("✅ Usuario por defecto creado: felipe@gmail.com");
        }
    }
}
