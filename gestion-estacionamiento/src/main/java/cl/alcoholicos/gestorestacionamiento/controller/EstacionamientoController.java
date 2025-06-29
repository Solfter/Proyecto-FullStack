package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.service.impl.EstacionamientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/estacionamientos")
@RequiredArgsConstructor
@Tag(name = "Estacionamientos", description = "API para la gestión de estacionamientos del sistema")
public class EstacionamientoController {
    
    private final EstacionamientoService estacionamientoService;

    @GetMapping
    @Operation(
        summary = "Obtener todos los estacionamientos",
        description = "Retorna una lista con todos los estacionamientos registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de estacionamientos obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EstacionamientoResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay estacionamientos registrados",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<List<EstacionamientoResponseDTO>> getAll() {
        List<EstacionamientoResponseDTO> estacionamientos = estacionamientoService.getAll();
        if (estacionamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estacionamientos);
    }

    @GetMapping("/id/{idEstacionamiento}")
    @Operation(
        summary = "Obtener estacionamiento por ID",
        description = "Retorna los datos de un estacionamiento específico usando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Estacionamiento encontrado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EstacionamientoResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Estacionamiento no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "ID inválido",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<EstacionamientoResponseDTO> getById(
        @Parameter(description = "ID del estacionamiento a buscar", required = true, example = "1")
        @PathVariable Integer idEstacionamiento) {
        EstacionamientoResponseDTO estacionamiento = estacionamientoService.getById(idEstacionamiento);
        if (estacionamiento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estacionamiento);
    }

    @SuppressWarnings("null")
    @PostMapping
    @Operation(
        summary = "Crear un nuevo estacionamiento",
        description = "Registra un nuevo estacionamiento en el sistema con los datos proporcionados"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Estacionamiento creado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EstacionamientoResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos o incompletos",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Conflicto con datos existentes",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "422",
            description = "Error de validación en los datos de entrada",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<?> insert(
        @Parameter(description = "Datos del estacionamiento a crear", required = true)
        @Valid @RequestBody EstacionamientoCreateDTO estacionamientoCreateDTO) {
        try {
            EstacionamientoResponseDTO nuevoEstacionamiento = estacionamientoService.insert(estacionamientoCreateDTO);
            return ResponseEntity.ok(nuevoEstacionamiento);   
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error: Datos incompletos - " + e.getRootCause().getMessage());
        }
    }

    @PutMapping("/{nroEstacionamiento}")
    @Operation(
        summary = "Actualizar estacionamiento",
        description = "Actualiza los datos de un estacionamiento existente identificado por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Estacionamiento actualizado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EstacionamientoResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Estacionamiento no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos o ID inválido",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<EstacionamientoResponseDTO> update(
        @Parameter(description = "Nro del estacionamiento a actualizar", required = true, example = "1")
        @PathVariable Integer nroEstacionamiento,
        @Parameter(description = "Datos del estacionamiento a actualizar", required = true)
        @RequestBody EstacionamientoUpdateDTO estacionamientoUpdateDTO) {
        EstacionamientoResponseDTO estacionamientoActualizado = estacionamientoService.update(nroEstacionamiento, estacionamientoUpdateDTO);
        if (estacionamientoActualizado == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estacionamientoActualizado);
    }

    @DeleteMapping("/{idEstacionamiento}")
    @Operation(
        summary = "Eliminar estacionamiento",
        description = "Elimina un estacionamiento del sistema usando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Estacionamiento eliminado exitosamente",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Estacionamiento no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "No se puede eliminar el estacionamiento porque está siendo usado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID del estacionamiento a eliminar", required = true, example = "1")
        @PathVariable Integer idEstacionamiento) {
        boolean eliminado = estacionamientoService.delete(idEstacionamiento);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estado/{estadoEstacionamiento}")
    public ResponseEntity<List<EstacionamientoResponseDTO>> obtenerEstacionamientosDisponibles (@PathVariable String estadoEstacionamiento) {
        List<EstacionamientoResponseDTO> estacionamientos = estacionamientoService.obtenerEstacionamientoPorEstado(estadoEstacionamiento);
        if (estacionamientos == null || estacionamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estacionamientos);
    }
}