package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.service.impl.TipoEstacionamientoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tipoestacionamiento")
@RequiredArgsConstructor
public class TipoEstacionamientoController {

    private final TipoEstacionamientoService tipoEstacionamientoService;

    @GetMapping
    public ResponseEntity<List<TipoEstacionamientoResponseDTO>> getAll() {
        List<TipoEstacionamientoResponseDTO> tipoEstacionamientos = tipoEstacionamientoService.getAll();
        if (tipoEstacionamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoEstacionamientos);
    }

    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody TipoEstacionamientoCreateDTO createDTO) {
        try {
            TipoEstacionamientoResponseDTO nuevoTipoEstacionamiento = tipoEstacionamientoService.insert(createDTO);
            return ResponseEntity.ok(nuevoTipoEstacionamiento);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error: Datos incompletos - " + e.getRootCause().getMessage());
        }
    }
}
