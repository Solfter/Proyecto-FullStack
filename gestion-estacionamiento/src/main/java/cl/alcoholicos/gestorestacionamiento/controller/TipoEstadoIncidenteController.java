package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoIncidenteResponseDTO;
import cl.alcoholicos.gestorestacionamiento.service.impl.TipoEstadoIncidenteService;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/tipoestadoincidente")
@RestController
@AllArgsConstructor
@Tag(name = "Tipos de Estado de Incidente", description = "API para la gestión de tipos de estado de incidentes del sistema")
public class TipoEstadoIncidenteController {
   
    private final TipoEstadoIncidenteService tipoEstadoIncidenteService;

    @GetMapping
    @Operation(
        summary = "Obtener todos los tipos de estado de incidente",
        description = "Retorna una lista con todos los tipos de estado de incidente registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de tipos de estado de incidente obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = TipoEstadoIncidenteResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay tipos de estado de incidente registrados",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<List<TipoEstadoIncidenteResponseDTO>> getAll() {
        List<TipoEstadoIncidenteResponseDTO> tipoEstadoIncidente = tipoEstadoIncidenteService.getAll();
        if (tipoEstadoIncidente.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoEstadoIncidente);
    }

    @DeleteMapping("/{idTipoEstadoIncidente}")
    @Operation(
        summary = "Eliminar tipo de estado de incidente",
        description = "Elimina un tipo de estado de incidente del sistema usando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Tipo de estado de incidente eliminado exitosamente",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tipo de estado de incidente no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "No se puede eliminar el tipo de estado de incidente porque está siendo usado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID del tipo de estado de incidente a eliminar", required = true, example = "1")
        @PathVariable Integer idTipoEstadoIncidente) {
        boolean deleted = tipoEstadoIncidenteService.delete(idTipoEstadoIncidente);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
