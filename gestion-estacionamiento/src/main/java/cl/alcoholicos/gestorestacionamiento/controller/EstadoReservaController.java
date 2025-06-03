package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.dto.EstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.service.impl.EstadoReservaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/estadoreserva")
@AllArgsConstructor

public class EstadoReservaController {

    private final EstadoReservaService estadoReservaService;

    @GetMapping
    public ResponseEntity<List<EstadoReservaResponseDTO>> getAll() {
        List<EstadoReservaResponseDTO> estadosReserva = estadoReservaService.getAll();
        if (estadosReserva.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estadosReserva);
    }

    @GetMapping("{idEstadoReserva}")
    public ResponseEntity<EstadoReservaResponseDTO> getById(@PathVariable Integer idEstadoReserva) {
        EstadoReservaResponseDTO estadoReserva = estadoReservaService.getById(idEstadoReserva);
        if (estadoReserva == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estadoReserva);
    }
}
