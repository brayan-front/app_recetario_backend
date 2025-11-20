// package com.example.recetario.model;
package com.recetario.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Preparation {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Recipe recipe;

    private Integer stepNumber;
    @Column(length = 2000)
    private String instructions;
}
