package cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.dto.EstadoEstacionamientoDTO;
import cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.service.impl.EstadoEstacionamientoService;

@RequestMapping("/estadoestacionamiento")
@RestController
public class EstadoEstacionamientoController {
    
    @Autowired
    private EstadoEstacionamientoService estadoEstacionamientoService;

    @GetMapping
    public ResponseEntity<List<EstadoEstacionamientoDTO>> getAll() {
        List<EstadoEstacionamientoDTO> estadoEstacionamientos = estadoEstacionamientoService.getAll();
        if (estadoEstacionamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estadoEstacionamientos);
    }

    @PostMapping
    public ResponseEntity<EstadoEstacionamientoDTO> insert(@RequestBody EstadoEstacionamientoDTO idEstadoEstacionamiento) {
        EstadoEstacionamientoDTO nuevoEstadoEstacionamiento = estadoEstacionamientoService.insert(idEstadoEstacionamiento);
        return ResponseEntity.ok(nuevoEstadoEstacionamiento);
    }

    @PutMapping("/{idEstadoEstacionamiento}")
    public ResponseEntity<EstadoEstacionamientoDTO> update(@PathVariable Integer idEstadoEstacionamiento, @RequestBody EstadoEstacionamientoDTO estadoEstacionamiento) {
        EstadoEstacionamientoDTO estadoEstacionamientoExistente = estadoEstacionamientoService.getById(idEstadoEstacionamiento);
        if (estadoEstacionamientoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        EstadoEstacionamientoDTO estadoEstacionamientoActualizado = estadoEstacionamientoService.update(idEstadoEstacionamiento, estadoEstacionamiento);
        return ResponseEntity.ok(estadoEstacionamientoActualizado);
    }

    @DeleteMapping("/{idEstadoEstacionamiento}")
    public ResponseEntity<EstadoEstacionamientoDTO> delete(@PathVariable Integer idEstadoEstacionamiento) {

        EstadoEstacionamientoDTO estadoEstacionamiento = estadoEstacionamientoService.getById(idEstadoEstacionamiento);
        if (estadoEstacionamiento == null) {
            return ResponseEntity.notFound().build();
        }

        EstadoEstacionamientoDTO estadoEstacionamientoBorrado = estadoEstacionamientoService.delete(idEstadoEstacionamiento);
        if (estadoEstacionamientoBorrado == null) {
            return ResponseEntity.ok(estadoEstacionamiento);
        }

        return ResponseEntity.badRequest().build();
    }
}
