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

import cl.alcoholicos.gestorestacionamiento.entity.IncidenteEntity;
import cl.alcoholicos.gestorestacionamiento.service.impl.IncidenteService;

@RequestMapping("/incidente")
@RestController
public class IncidenteController {
    @Autowired
    private IncidenteService incidenteService;

    @GetMapping
    public ResponseEntity<List<IncidenteEntity>> getAll() {
        List<IncidenteEntity> incidentes = incidenteService.getAll();
        if (incidentes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(incidentes);  
    }

    @PostMapping
    public ResponseEntity<IncidenteEntity> insert(@RequestBody IncidenteEntity incidente) {
        IncidenteEntity nuevoIncidente = incidenteService.insert(incidente);
        return ResponseEntity.ok(nuevoIncidente);
    }

    @PutMapping("/{idIncidente}")
    public ResponseEntity<IncidenteEntity> update(@PathVariable Integer idIncidente, @RequestBody IncidenteEntity incidente) {
        IncidenteEntity incidenteExistente = incidenteService.getById(idIncidente);
        if (incidenteExistente == null) {
            return ResponseEntity.notFound().build();
        }
        IncidenteEntity incidenteActualizado = incidenteService.update(idIncidente, incidente);
        return ResponseEntity.ok(incidenteActualizado);

    }

    @DeleteMapping("/{idIncidente}")
    public ResponseEntity<IncidenteEntity> delete (@PathVariable Integer idIncidente) {
        IncidenteEntity incidente = incidenteService.getById(idIncidente);
        if (incidente == null) {
            return ResponseEntity.notFound().build();
        }

        IncidenteEntity incidenteBorrado = incidenteService.delete(idIncidente);
        if (incidenteBorrado == null) {
            return ResponseEntity.ok(incidente);
        }

        return ResponseEntity.badRequest().build();
    }
}
