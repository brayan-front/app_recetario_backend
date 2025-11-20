package com.recetario.backend.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class AssignUserRequest {
    private UUID userId;
    private UUID roleId;
}
