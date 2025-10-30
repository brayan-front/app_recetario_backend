package com.example.recetario.repository;

import com.example.recetario.model.Unit;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Esta interfaz permite acceder a la base de datos sin escribir SQL manualmente
// JpaRepository ya tiene métodos listos como: save(), findAll(), findById(), delete(), etc.
@Repository
public interface UnitRepository extends JpaRepository<Unit, UUID> {  }

