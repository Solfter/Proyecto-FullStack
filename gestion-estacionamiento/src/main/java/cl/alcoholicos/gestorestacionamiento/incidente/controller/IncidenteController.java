package cl.alcoholicos.gestorestacionamiento.incidente.controller;

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

import cl.alcoholicos.gestorestacionamiento.incidente.dto.IncidenteDTO;
import cl.alcoholicos.gestorestacionamiento.incidente.service.impl.IncidenteService;

@RequestMapping("/incidente")
@RestController
public class IncidenteController {
    @Autowired
    private IncidenteService incidenteService;

    @GetMapping
    public ResponseEntity<List<IncidenteDTO>> getAll() {
        List<IncidenteDTO> incidentes = incidenteService.getAll();
        if (incidentes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(incidentes);  
    }

    @PostMapping
    public ResponseEntity<IncidenteDTO> insert(@RequestBody IncidenteDTO incidente) {
        IncidenteDTO nuevoIncidente = incidenteService.insert(incidente);
        return ResponseEntity.ok(nuevoIncidente);
    }

    @PutMapping("/{idIncidente}")
    public ResponseEntity<IncidenteDTO> update(@PathVariable Integer idIncidente, @RequestBody IncidenteDTO incidente) {
        IncidenteDTO incidenteExistente = incidenteService.getById(idIncidente);
        if (incidenteExistente == null) {
            return ResponseEntity.notFound().build();
        }
        IncidenteDTO incidenteActualizado = incidenteService.update(idIncidente, incidente);
        return ResponseEntity.ok(incidenteActualizado);

    }

    @DeleteMapping("/{idIncidente}")
    public ResponseEntity<IncidenteDTO> delete (@PathVariable Integer idIncidente) {
        IncidenteDTO incidente = incidenteService.getById(idIncidente);
        if (incidente == null) {
            return ResponseEntity.notFound().build();
        }

        IncidenteDTO incidenteBorrado = incidenteService.delete(idIncidente);
        if (incidenteBorrado == null) {
            return ResponseEntity.ok(incidente);
        }

        return ResponseEntity.badRequest().build();
    }
}
