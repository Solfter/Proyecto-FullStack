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

import cl.alcoholicos.gestorestacionamiento.entity.EstadoIncidenteEntity;
import cl.alcoholicos.gestorestacionamiento.service.impl.EstadoIncidenteService;

@RestController
@RequestMapping("/estadoincidente")
public class EstadoIncidenteController {
    
    @Autowired
    private EstadoIncidenteService estadoIncidenteService;

    @GetMapping
    public ResponseEntity<List<EstadoIncidenteEntity>> getAll() {
        List<EstadoIncidenteEntity> estadoIncidentes = estadoIncidenteService.getAll();
        if (estadoIncidentes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estadoIncidentes);
    }

    @PostMapping
    public ResponseEntity<EstadoIncidenteEntity> insert(@RequestBody EstadoIncidenteEntity idEstadoIncidente) {
        EstadoIncidenteEntity nuevoEstadoIncidente = estadoIncidenteService.insert(idEstadoIncidente);
        return ResponseEntity.ok(nuevoEstadoIncidente);
    }

    @PutMapping("/{idEstadoIncidente}")
    public ResponseEntity<EstadoIncidenteEntity> update(@PathVariable Integer idEstadoIncidente, @RequestBody EstadoIncidenteEntity estadoEstacionamiento) {
        EstadoIncidenteEntity estadoIncidenteExistente = estadoIncidenteService.getById(idEstadoIncidente);
        if (estadoIncidenteExistente == null) {
            return ResponseEntity.notFound().build();
        }
        EstadoIncidenteEntity estadoIncidenteActualizado = estadoIncidenteService.update(idEstadoIncidente, estadoEstacionamiento);
        return ResponseEntity.ok(estadoIncidenteActualizado);
    }

    @DeleteMapping("/{idEstadoIncidente}")
    public ResponseEntity<EstadoIncidenteEntity> delete(@PathVariable Integer idEstadoIncidente) {

        EstadoIncidenteEntity estadoIncidente = estadoIncidenteService.getById(idEstadoIncidente);
        if (estadoIncidente == null) {
            return ResponseEntity.notFound().build();
        }

        EstadoIncidenteEntity estadoIncidenteBorrado = estadoIncidenteService.delete(idEstadoIncidente);
        if (estadoIncidenteBorrado == null) {
            return ResponseEntity.ok(estadoIncidente);
        }

        return ResponseEntity.badRequest().build();
    }
}
