package com.recetario.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "units")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Unit {

    @Id
    @Column(name = "unit_id")
    private UUID id;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;
}
