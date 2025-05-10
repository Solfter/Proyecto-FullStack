package cl.alcoholicos.gestorestacionamiento.estacionamiento.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.service.impl.EstacionamientoService;
import jakarta.validation.Valid;
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

    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody EstacionamientoCreateDTO estacionamientoCreateDTO) {
        try {
            EstacionamientoResponseDTO nuevoEstacionamiento = estacionamientoService.insert(estacionamientoCreateDTO);
            return ResponseEntity.ok(nuevoEstacionamiento);   
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.badRequest().body("Error: Datos incompletos - " + e.getRootCause().getMessage());
            }
    }

    @PutMapping("/{idEstacionamiento}")
    public ResponseEntity<EstacionamientoResponseDTO> update(@PathVariable int idEstacionamiento, @RequestBody EstacionamientoUpdateDTO estacionamientoUpdateDTO) {
        EstacionamientoResponseDTO estacionamientoActualizado = estacionamientoService.update(idEstacionamiento, estacionamientoUpdateDTO);
        if (estacionamientoActualizado == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estacionamientoActualizado);
    }

    @DeleteMapping("/{idEstacionamiento}")
    public ResponseEntity<EstacionamientoEntity> delete (@PathVariable int idEstacionamiento) {
        boolean eliminado = estacionamientoService.delete(idEstacionamiento);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
