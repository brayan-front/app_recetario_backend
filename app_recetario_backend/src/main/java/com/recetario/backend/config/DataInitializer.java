package com.recetario.backend.config;

import com.recetario.backend.entities.Role;
import com.recetario.backend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        // =============================
        // ROLES POR DEFECTO
        // =============================
        createRoleIfNotExists("OWNER");
        createRoleIfNotExists("MANAGER");
        createRoleIfNotExists("CHEF");
        createRoleIfNotExists("WAITER");
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
}
