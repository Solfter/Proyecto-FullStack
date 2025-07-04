package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.entity.IncidenteEntity;
import cl.alcoholicos.gestorestacionamiento.service.impl.IncidenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/incidente")
@RestController
@Tag(name = "Incidentes", description = "API para la gestión de incidentes del sistema")
public class IncidenteController {
    @Autowired
    private IncidenteService incidenteService;

    @GetMapping
    @Operation(
        summary = "Obtener todos los incidentes",
        description = "Retorna una lista con todos los incidentes registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de incidentes obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = IncidenteEntity.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay incidentes registrados",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<List<IncidenteEntity>> getAll() {
        List<IncidenteEntity> incidentes = incidenteService.getAll();
        if (incidentes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(incidentes);  
    }

    @PostMapping
    @Operation(
        summary = "Crear un nuevo incidente",
        description = "Registra un nuevo incidente en el sistema con los datos proporcionados"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Incidente creado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = IncidenteEntity.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos o incompletos",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<IncidenteEntity> insert(
        @Parameter(description = "Datos del incidente a crear", required = true)
        @RequestBody IncidenteEntity incidente) {
        IncidenteEntity nuevoIncidente = incidenteService.insert(incidente);
        return ResponseEntity.ok(nuevoIncidente);
    }

    @PutMapping("/{idIncidente}")
    @Operation(
        summary = "Actualizar incidente",
        description = "Actualiza los datos de un incidente existente identificado por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Incidente actualizado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = IncidenteEntity.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Incidente no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<IncidenteEntity> update(
        @Parameter(description = "ID del incidente a actualizar", required = true, example = "1")
        @PathVariable Integer idIncidente, 
        @Parameter(description = "Datos del incidente a actualizar", required = true)
        @RequestBody IncidenteEntity incidente) {
        IncidenteEntity incidenteExistente = incidenteService.getById(idIncidente);
        if (incidenteExistente == null) {
            return ResponseEntity.notFound().build();
        }
        IncidenteEntity incidenteActualizado = incidenteService.update(idIncidente, incidente);
        return ResponseEntity.ok(incidenteActualizado);

    }

    @DeleteMapping("/{idIncidente}")
    @Operation(
        summary = "Eliminar incidente",
        description = "Elimina un incidente del sistema usando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Incidente eliminado exitosamente",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Incidente no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "No se puede eliminar el incidente porque está siendo usado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<IncidenteEntity> delete(
        @Parameter(description = "ID del incidente a eliminar", required = true, example = "1")
        @PathVariable Integer idIncidente) {
        IncidenteEntity incidente = incidenteService.getById(idIncidente);
        if (incidente == null) {
            return ResponseEntity.notFound().build();
        }

        boolean incidenteBorrado = incidenteService.delete(idIncidente);
        if (incidenteBorrado) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
