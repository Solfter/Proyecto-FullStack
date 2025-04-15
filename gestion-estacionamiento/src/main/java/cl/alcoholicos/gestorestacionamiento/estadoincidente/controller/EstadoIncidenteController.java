package cl.alcoholicos.gestorestacionamiento.estadoincidente.controller;

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

import cl.alcoholicos.gestorestacionamiento.estadoincidente.dto.EstadoIncidenteDTO;
import cl.alcoholicos.gestorestacionamiento.estadoincidente.service.impl.EstadoIncidenteService;

@RestController
@RequestMapping("/estadoincidente")
public class EstadoIncidenteController {
    
    @Autowired
    private EstadoIncidenteService estadoIncidenteService;

    @GetMapping
    public ResponseEntity<List<EstadoIncidenteDTO>> getAll() {
        List<EstadoIncidenteDTO> estadoIncidentes = estadoIncidenteService.getAll();
        if (estadoIncidentes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estadoIncidentes);
    }

    @PostMapping
    public ResponseEntity<EstadoIncidenteDTO> insert(@RequestBody EstadoIncidenteDTO idEstadoIncidente) {
        EstadoIncidenteDTO nuevoEstadoIncidente = estadoIncidenteService.insert(idEstadoIncidente);
        return ResponseEntity.ok(nuevoEstadoIncidente);
    }

    @PutMapping("/{idEstadoIncidente}")
    public ResponseEntity<EstadoIncidenteDTO> update(@PathVariable Integer idEstadoIncidente, @RequestBody EstadoIncidenteDTO estadoEstacionamiento) {
        EstadoIncidenteDTO estadoIncidenteExistente = estadoIncidenteService.getById(idEstadoIncidente);
        if (estadoIncidenteExistente == null) {
            return ResponseEntity.notFound().build();
        }
        EstadoIncidenteDTO estadoIncidenteActualizado = estadoIncidenteService.update(idEstadoIncidente, estadoEstacionamiento);
        return ResponseEntity.ok(estadoIncidenteActualizado);
    }

    @DeleteMapping("/{idEstadoIncidente}")
    public ResponseEntity<EstadoIncidenteDTO> delete(@PathVariable Integer idEstadoIncidente) {

        EstadoIncidenteDTO estadoIncidente = estadoIncidenteService.getById(idEstadoIncidente);
        if (estadoIncidente == null) {
            return ResponseEntity.notFound().build();
        }

        EstadoIncidenteDTO estadoIncidenteBorrado = estadoIncidenteService.delete(idEstadoIncidente);
        if (estadoIncidenteBorrado == null) {
            return ResponseEntity.ok(estadoIncidente);
        }

        return ResponseEntity.badRequest().build();
    }
}
