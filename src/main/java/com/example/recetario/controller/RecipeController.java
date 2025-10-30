package com.example.recetario.controller;

import com.example.recetario.model.Recipe;
import com.example.recetario.service.RecipeService;
import com.example.recetario.dto.RecipeRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService service;
    public RecipeController(RecipeService service){this.service = service;}

    @GetMapping
    public List<Recipe> listAll(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getOne(@PathVariable UUID id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Recipe> create(@RequestBody RecipeRequest req){
        Recipe r = service.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> update(@PathVariable UUID id, @RequestBody RecipeRequest req){
        Recipe r = service.update(id, req);
        return ResponseEntity.ok(r);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
