package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.service.impl.TipoEstacionamientoService;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tipoestacionamiento")
@RequiredArgsConstructor
@Tag(name = "Tipos de Estacionamiento", description = "API para la gestión de tipos de estacionamiento del sistema")
public class TipoEstacionamientoController {

    private final TipoEstacionamientoService tipoEstacionamientoService;

    @GetMapping
    @Operation(
        summary = "Obtener todos los tipos de estacionamiento",
        description = "Retorna una lista con todos los tipos de estacionamiento registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de tipos de estacionamiento obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = TipoEstacionamientoResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay tipos de estacionamiento registrados",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<List<TipoEstacionamientoResponseDTO>> getAll() {
        List<TipoEstacionamientoResponseDTO> tipoEstacionamientos = tipoEstacionamientoService.getAll();
        if (tipoEstacionamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoEstacionamientos);
    }

    @SuppressWarnings("null")
    @PostMapping
    @Operation(
        summary = "Crear un nuevo tipo de estacionamiento",
        description = "Registra un nuevo tipo de estacionamiento en el sistema con los datos proporcionados"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tipo de estacionamiento creado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = TipoEstacionamientoResponseDTO.class)
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
        @Parameter(description = "Datos del tipo de estacionamiento a crear", required = true)
        @RequestBody TipoEstacionamientoCreateDTO createDTO) {
        try {
            TipoEstacionamientoResponseDTO nuevoTipoEstacionamiento = tipoEstacionamientoService.insert(createDTO);
            return ResponseEntity.ok(nuevoTipoEstacionamiento);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error: Datos incompletos - " + e.getRootCause().getMessage());
        }
    }

    @DeleteMapping("/{idTipoEstacionamiento}")
    @Operation(
        summary = "Eliminar tipo de estacionamiento",
        description = "Elimina un tipo de estacionamiento del sistema usando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Tipo de estacionamiento eliminado exitosamente",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tipo de estacionamiento no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "No se puede eliminar el tipo de estacionamiento porque está siendo usado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID del tipo de estacionamiento a eliminar", required = true, example = "1")
        @PathVariable Integer idTipoEstacionamiento) {
        boolean deleted = tipoEstacionamientoService.delete(idTipoEstacionamiento);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
