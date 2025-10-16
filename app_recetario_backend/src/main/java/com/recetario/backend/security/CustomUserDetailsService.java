package com.recetario.backend.security;

import com.recetario.backend.entities.RestaurantUser;
import com.recetario.backend.entities.User;
import com.recetario.backend.repositories.RestaurantUserRepository;
import com.recetario.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantUserRepository restaurantUserRepository;

    /**
     * Carga un usuario para Spring Security.
     * El parámetro username puede venir con contexto de restaurante: "username:restaurantId"
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Separar posible contexto "username:restaurantId"
        final String[] usernameParts = username.split(":");
        final String actualUsername = usernameParts[0];
        final String restaurantId = (usernameParts.length > 1 && !usernameParts[1].isBlank())
                ? usernameParts[1]
                : null;

        // Buscar usuario por username
        User user = userRepository.findByUsername(actualUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + actualUsername));

        if (user.getActive() != null && !user.getActive()) {
            throw new UsernameNotFoundException("Usuario inactivo: " + actualUsername);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (restaurantId != null) {
            // Buscar la asignación específica por userId y restaurantId
            Optional<RestaurantUser> ruOpt = restaurantUserRepository.findByUser_UserIdAndRestaurantId(user.getUserId(), restaurantId);
            RestaurantUser ru = ruOpt.orElseThrow(() -> new UsernameNotFoundException("Usuario no asignado al restaurante especificado"));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + ru.getRole().getName()));
        } else {
            // Obtener todas las asignaciones del usuario y mapear a roles
            List<RestaurantUser> userRestaurants = restaurantUserRepository.findByUser_UserId(user.getUserId());
            authorities = userRestaurants.stream()
                    .map(ru -> new SimpleGrantedAuthority("ROLE_" + ru.getRole().getName()))
                    .distinct()
                    .collect(Collectors.toList());
        }

        // Retornar implementación de UserDetails usada por Spring Security
    return new org.springframework.security.core.userdetails.User(
        username,
        user.getPassword(),
        authorities
    );
    }
}
