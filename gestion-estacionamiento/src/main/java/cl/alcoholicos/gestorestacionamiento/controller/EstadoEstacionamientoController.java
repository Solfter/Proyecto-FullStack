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

import cl.alcoholicos.gestorestacionamiento.dto.EstadoEstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstadoEstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.service.impl.EstadoEstacionamientoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;

@RequestMapping("/estadoestacionamiento")
@RestController
@AllArgsConstructor
@Tag(name = "Estados de Estacionamiento", description = "API para consultar los estados disponibles de los espacios de estacionamiento")
public class EstadoEstacionamientoController {

    private final EstadoEstacionamientoService estadoEstacionamientoService;

    @Operation(summary = "Obtener todos los estados de estacionamiento", description = "Retorna una lista con todos los estados disponibles para los espacios de estacionamiento "
            +
            "(ej: Disponible, Ocupado, Reservado, Fuera de Servicio, etc.). " +
            "Esta información es útil para conocer los posibles estados que pueden tener los espacios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de estados de estacionamiento obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstadoEstacionamientoResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "No se encontraron estados de estacionamiento configurados en el sistema", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al obtener los estados", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<EstadoEstacionamientoResponseDTO>> getAll() {
        List<EstadoEstacionamientoResponseDTO> estadoEstacionamientos = estadoEstacionamientoService.getAll();
        if (estadoEstacionamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estadoEstacionamientos);
    }

    @PostMapping
    @Operation(
        summary = "Crear un nuevo estado de estacionamiento",
        description = "Registra un nuevo estado de estacionamiento en el sistema con los datos proporcionados"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Estado de estacionamiento creado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EstadoEstacionamientoResponseDTO.class)
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
        @Parameter(description = "Datos del estado de estacionamiento a crear", required = true)
        @RequestBody EstadoEstacionamientoCreateDTO createDTO) {

        try {
            EstadoEstacionamientoResponseDTO estadoEstacionamiento = estadoEstacionamientoService.insert(createDTO);
            return ResponseEntity.ok(estadoEstacionamiento);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error: Datos incompletos - " + e.getRootCause().getMessage());
        }
    }

    @DeleteMapping("/{idEstadoEstacionamiento}")
    @Operation(
        summary = "Eliminar estado de estacionamiento",
        description = "Elimina un estado de estacionamiento del sistema usando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Estado de estacionamiento eliminado exitosamente",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Estado de estacionamiento no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "No se puede eliminar el estado de estacionamiento porque está siendo usado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID del estado de estacionamiento a eliminar", required = true, example = "1")
        @PathVariable Integer idEstadoEstacionamiento) {
        boolean deleted = estadoEstacionamientoService.delete(idEstadoEstacionamiento);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
