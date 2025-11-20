//package com.example.recetario.service;
package com.recetario.backend.services;

//import com.example.recetario.model.Unit;
//import com.example.recetario.repository.UnitRepository;
import com.recetario.backend.entities.Unit;
import com.recetario.backend.repositories.UnitRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List; // ← esta línea debe estar presente y sin errores

@Service
public class UnitService {
    // Inyectamos el repositorio para poder usar sus métodos
    private final UnitRepository repo;

    //constructor para que el repositorio inicie
    public UnitService(UnitRepository repo){

        this.repo = repo;
    
    }

      // Método para listar todas las unidades
    public List<Unit> findAll() {
        return repo.findAll();
    }

     public Unit save(Unit unit) {
        return repo.save(unit);
    }

     // Endpoint GET → Devuelve la lista de todas las unidades
    // Ejemplo: GET http://localhost:8080/units
    @GetMapping
    public List<Unit> list() {
        return  repo.findAll();
    }

    // Endpoint POST → Guarda una nueva unidad en la base de datos
    // Ejemplo: POST http://localhost:8095/units
    // Body JSON: { "name": "gramos" }
    @PostMapping
    public Unit create(@RequestBody Unit unit) {
        // @RequestBody convierte el JSON recibido en un objeto Unit automáticamente
        return repo.save(unit);
    }





}

