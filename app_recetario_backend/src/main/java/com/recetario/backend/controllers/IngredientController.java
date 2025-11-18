//package com.example.recetario.controller;
package com.recetario.backend.controllers;

//import com.example.recetario.model.Ingredient;
//import com.example.recetario.service.IngredientService;
import com.recetario.backend.entities.Ingredient;
import com.recetario.backend.services.IngredientService;

import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService service;
    public IngredientController(IngredientService service){this.service = service;}

    @PostMapping
    public ResponseEntity<Ingredient> create(@Valid @RequestBody Ingredient i){ return ResponseEntity.status(HttpStatus.CREATED).body(service.create(i)); }

    @GetMapping
    public List<Ingredient> list(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> get(@PathVariable UUID id){ return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable UUID id, @Valid @RequestBody Ingredient changes){ return ResponseEntity.ok(service.update(id, changes)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
