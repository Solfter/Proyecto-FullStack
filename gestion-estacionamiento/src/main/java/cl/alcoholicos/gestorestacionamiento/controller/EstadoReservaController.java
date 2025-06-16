package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.dto.EstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;
import cl.alcoholicos.gestorestacionamiento.repository.ReservaRepository;
import cl.alcoholicos.gestorestacionamiento.service.impl.EstadoReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/estadoreserva")
@AllArgsConstructor
@Tag(name = "Estados de Reserva", description = "API para consultar los estados e historial de las reservas")
public class EstadoReservaController {

    private final EstadoReservaService estadoReservaService;
    private final ReservaRepository reservaRepository;

    @Operation(
        summary = "Obtener todos los estados de reserva",
        description = "Retorna una lista con todos los estados de reserva registrados en el sistema. " +
                     "Esto incluye el historial completo de cambios de estado de todas las reservas."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de estados de reserva obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EstadoReservaResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No se encontraron estados de reserva registrados",
            content = @Content
        )
    })
    @GetMapping
    public ResponseEntity<List<EstadoReservaResponseDTO>> getAll() {
        List<EstadoReservaResponseDTO> estadosReserva = estadoReservaService.getAll();
        if (estadosReserva.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estadosReserva);
    }

    @Operation(
        summary = "Obtener historial de estados por ID de reserva",
        description = "Retorna el historial completo de cambios de estado para una reserva específica. " +
                     "Incluye todos los estados por los que ha pasado la reserva, ordenados cronológicamente."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Historial de estados de la reserva obtenido exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EstadoReservaResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "No se encontraron estados para la reserva con el ID proporcionado, o la reserva no existe",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "ID de reserva inválido",
            content = @Content
        )
    })
    @GetMapping("{idReserva}")
    public ResponseEntity<List<EstadoReservaResponseDTO>> getByIdReserva(
        @Parameter(
            description = "ID único de la reserva para obtener su historial de estados",
            required = true,
            example = "1"
        )
        @PathVariable Integer idReserva
    ) {
        List<EstadoReservaResponseDTO> estadoReserva = estadoReservaService.getByIdReserva(idReserva);
        if (estadoReserva == null || estadoReserva.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estadoReserva);
    }

    @PutMapping("{idReserva}/activar")
    public ResponseEntity<Void> actualizarReservaAActiva (@PathVariable Integer idReserva) {
        ReservaEntity reserva = reservaRepository.findById(idReserva)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        
        estadoReservaService.actualizarAEstadoActiva(reserva);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{idReserva}/cancelar")
    public ResponseEntity<Void> actualizarReservaACancelada (@PathVariable Integer idReserva) {
        ReservaEntity reserva = reservaRepository.findById(idReserva)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        
        estadoReservaService.actualizarAEstadoCancelada(reserva);
        return ResponseEntity.noContent().build();
    }

    
}