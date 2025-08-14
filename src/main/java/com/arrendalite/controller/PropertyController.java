package com.arrendalite.controller;

import com.arrendalite.model.Property;
import com.arrendalite.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/property")
@AllArgsConstructor
@Tag(name = "Propiedades", description = "API para gestionar propiedades disponibles para arriendo")
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping
    @Operation(
        summary = "Obtener todas las propiedades",
        description = "Retorna una lista de todas las propiedades disponibles para arriendo"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de propiedades obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Property.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor"
        )
    })
    public ResponseEntity<List<Property>> list() {
        List<Property> properties = propertyService.propertyList();
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener propiedad por ID",
        description = "Retorna una propiedad específica basada en su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Propiedad encontrada exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Property.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Propiedad no encontrada"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor"
        )
    })
    public ResponseEntity<Property> getPropertyById(
        @Parameter(description = "ID de la propiedad", required = true, example = "1")
        @PathVariable Long id
    ) {
        Property property = propertyService.findById(id);
        return ResponseEntity.ok(property);
    }



}
