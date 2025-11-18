//package com.example.recetario.controller;
package com.recetario.backend.controllers;

//import com.example.recetario.model.Unit;
//import com.example.recetario.service.UnitService;
import com.recetario.backend.entities.Unit;
import com.recetario.backend.services.UnitService;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/units")
public class UnitController {
    private final UnitService service;
    public UnitController(UnitService service){this.service = service;}
    @GetMapping
    public List<Unit> list(){ return service.findAll(); }

     // ✅ Método POST → crear nueva unidad
    // Esto es lo que te falta si te da 405 Method Not Allowed
    @PostMapping
    public Unit create(@RequestBody Unit unit) {
        // @RequestBody convierte el JSON que mandas desde Postman en un objeto Unit
        return service.save(unit); // guarda en la base de datos
    }
}
