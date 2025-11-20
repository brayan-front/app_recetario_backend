//package com.example.recetario.service;
package com.recetario.backend.services;

//import com.example.recetario.model.Ingredient;
//import com.example.recetario.model.Unit;
//import com.example.recetario.repository.IngredientRepository;
//import com.example.recetario.repository.UnitRepository;
import com.recetario.backend.entities.Ingredient;
import com.recetario.backend.entities.Unit;
import com.recetario.backend.repositories.IngredientRepository;
import com.recetario.backend.repositories.UnitRepository;

import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.*;

@Service
public class IngredientService {
    private final IngredientRepository repo;
    private final UnitRepository unitRepo;

    public IngredientService(IngredientRepository repo, UnitRepository unitRepo) {
        this.repo = repo;
        this.unitRepo = unitRepo;
    }

    public Ingredient create(Ingredient i) {

        // Si enviaron defaultUnit, verificamos que existe
        if (i.getDefaultUnit() != null && i.getDefaultUnit().getId() != null) {
            Unit unit = unitRepo.findById(i.getDefaultUnit().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Unit not found"));
            i.setDefaultUnit(unit);
        }

        return repo.save(i);
    }

    public List<Ingredient> findAll() { return repo.findAll(); }

    public Optional<Ingredient> findById(UUID id) { return repo.findById(id); }

    public Ingredient update(UUID id, Ingredient changes){
        Ingredient e = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));

        if (changes.getDescription()!=null) e.setDescription(changes.getDescription());
        if (changes.getName()!=null) e.setName(changes.getName());
        if (changes.getCaloriesPerUnit()!=null) e.setCaloriesPerUnit(changes.getCaloriesPerUnit());

        if (changes.getDefaultUnit()!=null && changes.getDefaultUnit().getId() != null) {
            Unit unit = unitRepo.findById(changes.getDefaultUnit().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Unit not found"));
            e.setDefaultUnit(unit);
        }

        return repo.save(e);
    }

    public void delete(UUID id){
        if (!repo.existsById(id)) throw new IllegalArgumentException("Ingredient not found");
        repo.deleteById(id);
    }
}
