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

import cl.alcoholicos.gestorestacionamiento.entity.EstadoIncidenteEntity;
import cl.alcoholicos.gestorestacionamiento.service.impl.EstadoIncidenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/estadoincidente")
@Tag(name = "Estados de Incidente", description = "API para la gestión de estados de incidentes del sistema")
public class EstadoIncidenteController {
    
    @Autowired
    private EstadoIncidenteService estadoIncidenteService;

    @GetMapping
    @Operation(
        summary = "Obtener todos los estados de incidente",
        description = "Retorna una lista con todos los estados de incidente registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de estados de incidente obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EstadoIncidenteEntity.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay estados de incidente registrados",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<List<EstadoIncidenteEntity>> getAll() {
        List<EstadoIncidenteEntity> estadoIncidentes = estadoIncidenteService.getAll();
        if (estadoIncidentes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estadoIncidentes);
    }

    @PostMapping
    @Operation(
        summary = "Crear un nuevo estado de incidente",
        description = "Registra un nuevo estado de incidente en el sistema con los datos proporcionados"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Estado de incidente creado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EstadoIncidenteEntity.class)
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
    public ResponseEntity<EstadoIncidenteEntity> insert(
        @Parameter(description = "Datos del estado de incidente a crear", required = true)
        @RequestBody EstadoIncidenteEntity idEstadoIncidente) {
        EstadoIncidenteEntity nuevoEstadoIncidente = estadoIncidenteService.insert(idEstadoIncidente);
        return ResponseEntity.ok(nuevoEstadoIncidente);
    }

    @PutMapping("/{idEstadoIncidente}")
    @Operation(
        summary = "Actualizar estado de incidente",
        description = "Actualiza los datos de un estado de incidente existente identificado por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Estado de incidente actualizado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EstadoIncidenteEntity.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Estado de incidente no encontrado",
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
    public ResponseEntity<EstadoIncidenteEntity> update(
        @Parameter(description = "ID del estado de incidente a actualizar", required = true, example = "1")
        @PathVariable Integer idEstadoIncidente, 
        @Parameter(description = "Datos del estado de incidente a actualizar", required = true)
        @RequestBody EstadoIncidenteEntity estadoEstacionamiento) {
        EstadoIncidenteEntity estadoIncidenteExistente = estadoIncidenteService.getById(idEstadoIncidente);
        if (estadoIncidenteExistente == null) {
            return ResponseEntity.notFound().build();
        }
        EstadoIncidenteEntity estadoIncidenteActualizado = estadoIncidenteService.update(idEstadoIncidente, estadoEstacionamiento);
        return ResponseEntity.ok(estadoIncidenteActualizado);
    }

    @DeleteMapping("/{idEstadoIncidente}")
    @Operation(
        summary = "Eliminar estado de incidente",
        description = "Elimina un estado de incidente del sistema usando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Estado de incidente eliminado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EstadoIncidenteEntity.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Estado de incidente no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "No se puede eliminar el estado de incidente porque está siendo usado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<EstadoIncidenteEntity> delete(
        @Parameter(description = "ID del estado de incidente a eliminar", required = true, example = "1")
        @PathVariable Integer idEstadoIncidente) {

        EstadoIncidenteEntity estadoIncidente = estadoIncidenteService.getById(idEstadoIncidente);
        if (estadoIncidente == null) {
            return ResponseEntity.notFound().build();
        }

        EstadoIncidenteEntity estadoIncidenteBorrado = estadoIncidenteService.delete(idEstadoIncidente);
        if (estadoIncidenteBorrado == null) {
            return ResponseEntity.ok(estadoIncidente);
        }

        return ResponseEntity.badRequest().build();
    }
}
