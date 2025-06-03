package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.dto.EstadoEstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.service.impl.EstadoEstacionamientoService;
import lombok.AllArgsConstructor;

@RequestMapping("/estadoestacionamiento")
@RestController
@AllArgsConstructor
public class EstadoEstacionamientoController {
    
    private final EstadoEstacionamientoService estadoEstacionamientoService;

    @GetMapping
    public ResponseEntity<List<EstadoEstacionamientoResponseDTO>> getAll() {
        List<EstadoEstacionamientoResponseDTO> estadoEstacionamientos = estadoEstacionamientoService.getAll();
        if (estadoEstacionamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estadoEstacionamientos);
    }

}
