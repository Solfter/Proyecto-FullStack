package cl.alcoholicos.gestorestacionamiento.estacionamiento.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.service.impl.EstacionamientoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/estacionamientos")
@RequiredArgsConstructor
public class EstacionamientoController {
    
    private final EstacionamientoService estacionamientoService;

    @GetMapping
    public ResponseEntity<List<EstacionamientoResponseDTO>> getAll() {
        List<EstacionamientoResponseDTO> estacionamientos = estacionamientoService.getAll();
        if (estacionamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estacionamientos);
    }

    @GetMapping("{idEstacionamiento}")
    public ResponseEntity<EstacionamientoResponseDTO> getById(@PathVariable Integer idEstacionamiento) {
        EstacionamientoResponseDTO estacionamiento = estacionamientoService.getById(idEstacionamiento);
        if (estacionamiento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estacionamiento);
    }
}
