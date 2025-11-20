package com.recetario.backend.devops;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

@RestController
public class DevOpsController {

    @GetMapping("/devops/health")
    public Map<String, Object> health() {
    Map<String, Object> response = new HashMap<>();
    response.put("status", "UP");
    response.put("service", "app-recetario-backend");
    response.put("timestamp", LocalDateTime.now().toString());
    return response;
    }

}
