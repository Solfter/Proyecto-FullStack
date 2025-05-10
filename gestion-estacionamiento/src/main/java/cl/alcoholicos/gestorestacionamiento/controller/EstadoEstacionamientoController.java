package cl.alcoholicos.gestorestacionamiento.controller;

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

import cl.alcoholicos.gestorestacionamiento.entity.EstadoEstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.service.impl.EstadoEstacionamientoService;

@RequestMapping("/estadoestacionamiento")
@RestController
public class EstadoEstacionamientoController {
    
    @Autowired
    private EstadoEstacionamientoService estadoEstacionamientoService;

    @GetMapping
    public ResponseEntity<List<EstadoEstacionamientoEntity>> getAll() {
        List<EstadoEstacionamientoEntity> estadoEstacionamientos = estadoEstacionamientoService.getAll();
        if (estadoEstacionamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estadoEstacionamientos);
    }

    @PostMapping
    public ResponseEntity<EstadoEstacionamientoEntity> insert(@RequestBody EstadoEstacionamientoEntity idEstadoEstacionamiento) {
        EstadoEstacionamientoEntity nuevoEstadoEstacionamiento = estadoEstacionamientoService.insert(idEstadoEstacionamiento);
        return ResponseEntity.ok(nuevoEstadoEstacionamiento);
    }

    @PutMapping("/{idEstadoEstacionamiento}")
    public ResponseEntity<EstadoEstacionamientoEntity> update(@PathVariable Integer idEstadoEstacionamiento, @RequestBody EstadoEstacionamientoEntity estadoEstacionamiento) {
        EstadoEstacionamientoEntity estadoEstacionamientoExistente = estadoEstacionamientoService.getById(idEstadoEstacionamiento);
        if (estadoEstacionamientoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        EstadoEstacionamientoEntity estadoEstacionamientoActualizado = estadoEstacionamientoService.update(idEstadoEstacionamiento, estadoEstacionamiento);
        return ResponseEntity.ok(estadoEstacionamientoActualizado);
    }

    @DeleteMapping("/{idEstadoEstacionamiento}")
    public ResponseEntity<EstadoEstacionamientoEntity> delete(@PathVariable Integer idEstadoEstacionamiento) {

        EstadoEstacionamientoEntity estadoEstacionamiento = estadoEstacionamientoService.getById(idEstadoEstacionamiento);
        if (estadoEstacionamiento == null) {
            return ResponseEntity.notFound().build();
        }

        EstadoEstacionamientoEntity estadoEstacionamientoBorrado = estadoEstacionamientoService.delete(idEstadoEstacionamiento);
        if (estadoEstacionamientoBorrado == null) {
            return ResponseEntity.ok(estadoEstacionamiento);
        }

        return ResponseEntity.badRequest().build();
    }
}
