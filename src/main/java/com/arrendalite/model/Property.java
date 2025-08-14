package com.arrendalite.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "property")
@Schema(description = "Modelo de Propiedad")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la propiedad", example = "1")
    private Long id;

    @Schema(description = "Nombre de la propiedad", example = "Casa en la playa")
    private String name;

    @Schema(description = "Precio diario de la propiedad", example = "150.00")
    private Double dailyPrice;


}
