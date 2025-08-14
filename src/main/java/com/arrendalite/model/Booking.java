package com.arrendalite.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Schema(description = "Modelo de Reserva")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la reserva", example = "1")
    private Long id;

    @Schema(description = "Fecha de inicio de la reserva", example = "2024-01-15")
    private LocalDate startDate;

    @Schema(description = "Fecha de fin de la reserva", example = "2024-01-20")
    private LocalDate endDate;

    @Schema(description = "Precio total de la reserva", example = "750.00")
    private Double totalPrice;

    @Schema(description = "Indica si la reserva está cancelada", example = "false")
    private Boolean isCancelled;

    @Schema(description = "Nombre del cliente", example = "Juan Pérez")
    private String customerName;

    @ManyToOne
    @JoinColumn(name = "property_id")
    @Schema(description = "Propiedad asociada a la reserva")
    private Property property;

}
