package com.arrendalite.controller;

import com.arrendalite.model.Booking;
import com.arrendalite.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
@Tag(name = "Reservas", description = "API para gestionar reservas de propiedades")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @Operation(
        summary = "Crear una nueva reserva",
        description = "Crea una nueva reserva para una propiedad con validaciones de fechas y cálculo de precio"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Reserva creada exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Booking.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de reserva inválidos"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor"
        )
    })
    public ResponseEntity<Booking> create(
        @Parameter(description = "Datos de la reserva", required = true)
        @RequestBody Booking booking
    ) {
        Booking createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.status(201).body(createdBooking);
    }

    @GetMapping
    @Operation(
        summary = "Obtener todas las reservas",
        description = "Retorna una lista de todas las reservas existentes"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de reservas obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Booking.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor"
        )
    })
    public ResponseEntity<List<Booking>> list() {
        List<Booking> bookings = bookingService.bookingList();
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/{id}/cancel")
    @Operation(
        summary = "Cancelar una reserva",
        description = "Cancela una reserva existente por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Reserva cancelada exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Booking.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Reserva no encontrada"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "La reserva ya está cancelada"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor"
        )
    })
    public ResponseEntity<Booking> cancel(
        @Parameter(description = "ID de la reserva a cancelar", required = true, example = "1")
        @PathVariable Long id
    ) {
        Booking cancelledBooking = bookingService.cancelBooking(id);
        return ResponseEntity.ok(cancelledBooking);
    }
}
