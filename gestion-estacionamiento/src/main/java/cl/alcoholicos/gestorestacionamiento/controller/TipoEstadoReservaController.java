package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.service.impl.TipoEstadoReservaService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/tipoestadoreserva")
public class TipoEstadoReservaController {
    
    private final TipoEstadoReservaService tipoEstadoReservaService;

    @GetMapping
    public ResponseEntity<List<TipoEstadoReservaResponseDTO>> getAll() {
        List<TipoEstadoReservaResponseDTO> tipoEstadoReservas = tipoEstadoReservaService.getAll();
        if (tipoEstadoReservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoEstadoReservas);
    }

}
