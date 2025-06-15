package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.service.impl.TipoEstadoReservaService;
import lombok.AllArgsConstructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@AllArgsConstructor
@RequestMapping("/tipoestadoreserva")
@Tag(name = "Tipo Estado Reserva", description = "API para gestionar los tipos de estado de reserva")
public class TipoEstadoReservaController {
    
    private final TipoEstadoReservaService tipoEstadoReservaService;

    @GetMapping
    @Operation(
        summary = "Obtener todos los tipos de estado de reserva",
        description = "Retorna una lista con todos los tipos de estado de reserva disponibles en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de tipos de estado de reserva obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = TipoEstadoReservaResponseDTO.class))
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay tipos de estado de reserva disponibles",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<List<TipoEstadoReservaResponseDTO>> getAll() {
        List<TipoEstadoReservaResponseDTO> tipoEstadoReservas = tipoEstadoReservaService.getAll();
        if (tipoEstadoReservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoEstadoReservas);
    }

}
