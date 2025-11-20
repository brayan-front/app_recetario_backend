package com.recetario.backend.services;

import com.recetario.backend.dto.AssignUserRequest;
import com.recetario.backend.dto.RestaurantUserResponse;
import com.recetario.backend.entities.RestaurantUser;
import com.recetario.backend.entities.User;
import com.recetario.backend.entities.Role;
import com.recetario.backend.repositories.RestaurantUserRepository;
import com.recetario.backend.repositories.UserRepository;
import com.recetario.backend.repositories.RoleRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantUserService {

    private final RestaurantUserRepository repository;
    //private final RestaurantUserRepository restaurantUserRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RestaurantUserService(
            RestaurantUserRepository repository,
            UserRepository userRepository,
            RoleRepository roleRepository
    ) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public RestaurantUser assignUser(UUID restaurantId, AssignUserRequest request) {

        // Validar usuario
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Validar rol (opcional)
        Role role = null;
        if (request.getRoleId() != null) {
            role = roleRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
        }

        // Validar si ya existe asignación
        Optional<RestaurantUser> existing = 
                repository.findByUser_UserIdAndRestaurantId(request.getUserId(), restaurantId);

        if (existing.isPresent()) {
            throw new RuntimeException("User already assigned to this restaurant");
        }

        // Crear asignación
        RestaurantUser ru = RestaurantUser.builder()
                .restaurantId(restaurantId)
                .user(user)
                .role(role)
                .joinedAt(OffsetDateTime.now())
                .build();

        return repository.save(ru);
    }

     public List<RestaurantUserResponse> getUsersByRestaurant(UUID restaurantId) {
        List<RestaurantUser> assignments = repository.findByRestaurantId(restaurantId);

        return assignments.stream().map(ru -> new RestaurantUserResponse(
                ru.getRestaurantUserId(),
                ru.getUser().getUserId(),
                ru.getUser().getFullName(),
                ru.getUser().getEmail(),
                ru.getRole().getRoleId(),
                ru.getRole().getName(),
                ru.getJoinedAt()
        )).toList();
    }


}
