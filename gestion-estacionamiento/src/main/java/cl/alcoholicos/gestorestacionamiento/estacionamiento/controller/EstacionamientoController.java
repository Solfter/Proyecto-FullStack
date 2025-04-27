package cl.alcoholicos.gestorestacionamiento.estacionamiento.controller;

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

import cl.alcoholicos.gestorestacionamiento.estacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.service.impl.EstacionamientoService;

@RestController
@RequestMapping("/estacionamiento")
public class EstacionamientoController {
    
    @Autowired
    private EstacionamientoService estacionamientoService;

    @GetMapping
    public ResponseEntity<List<EstacionamientoEntity>> getAll() {
        List<EstacionamientoEntity> estacionamientos = estacionamientoService.getAll();
        if (estacionamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estacionamientos);
    }

    @PostMapping
    public ResponseEntity<EstacionamientoEntity> insert(@RequestBody EstacionamientoEntity estacionamiento) {
        EstacionamientoEntity nuevoEstacionamiento = estacionamientoService.insert(estacionamiento);
        return ResponseEntity.ok(nuevoEstacionamiento);
    }

    @PutMapping("/{idEstacionamiento}")
    public ResponseEntity<EstacionamientoEntity> update(@PathVariable Integer idEstacionamiento, @RequestBody EstacionamientoEntity estacionamiento) {
        EstacionamientoEntity estacionamientoExistente = estacionamientoService.getById(idEstacionamiento);
        if (estacionamientoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        EstacionamientoEntity estacionamientoActualizado = estacionamientoService.update(idEstacionamiento, estacionamiento);
        return ResponseEntity.ok(estacionamientoActualizado);
    }

    @DeleteMapping("/{idEstacionamiento}")
    public ResponseEntity<EstacionamientoEntity> delete(@PathVariable Integer idEstacionamiento) {

        EstacionamientoEntity estacionamiento = estacionamientoService.getById(idEstacionamiento);
        if (estacionamiento == null) {
            return ResponseEntity.notFound().build();
        }

        EstacionamientoEntity estacionamientoBorrado = estacionamientoService.delete(idEstacionamiento);
        if (estacionamientoBorrado == null) {
            return ResponseEntity.ok(estacionamiento);
        }

        return ResponseEntity.badRequest().build();
    }
}
