package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoIncidenteResponseDTO;
import cl.alcoholicos.gestorestacionamiento.service.impl.TipoEstadoIncidenteService;
import lombok.AllArgsConstructor;

@RequestMapping("/tipoestadoincidente")
@RestController
@AllArgsConstructor
public class TipoEstadoIncidenteController {
   
    private final TipoEstadoIncidenteService tipoEstadoIncidenteService;

    @GetMapping
    public ResponseEntity<List<TipoEstadoIncidenteResponseDTO>> getAll() {
        List<TipoEstadoIncidenteResponseDTO> tipoEstadoIncidente = tipoEstadoIncidenteService.getAll();
        if (tipoEstadoIncidente.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoEstadoIncidente);
    }
}
