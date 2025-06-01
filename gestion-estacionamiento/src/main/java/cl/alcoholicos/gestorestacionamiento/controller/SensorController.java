package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import cl.alcoholicos.gestorestacionamiento.dto.SensorCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.SensorResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;
import cl.alcoholicos.gestorestacionamiento.service.impl.SensorService;

@RequestMapping("/sensor")
@RestController
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public ResponseEntity<List<SensorResponseDTO>> getAll() {
        List<SensorResponseDTO> sensores = sensorService.getAll();
        if (sensores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sensores);
    }

    @GetMapping("/{idSensor}")
    public ResponseEntity<SensorResponseDTO> getById(@PathVariable Integer idSensor) {
        SensorResponseDTO sensor = sensorService.getById(idSensor);
        if (sensor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sensor);
    }

    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody SensorCreateDTO createDTO) {
        try{
            SensorResponseDTO nuevoSensor = sensorService.insert(createDTO);
            return ResponseEntity.ok(nuevoSensor);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error: Datos incompletos - " + e.getRootCause().getMessage());
        }
    }

    @PutMapping("/{idSensor}")
    public ResponseEntity<SensorEntity> update(@PathVariable Integer idSensor, @RequestBody SensorEntity sensor) {
        /*SensorEntity sensorExistente = sensorService.getById(idSensor);
        if (sensorExistente == null) {
            return ResponseEntity.notFound().build();
        }
        SensorEntity sensorActualizado = sensorService.update(idSensor, sensor);
        return ResponseEntity.ok(sensorActualizado);*/
        return null;
    }

    @DeleteMapping("/{idSensor}")
    public ResponseEntity<Void> delete(@PathVariable Integer idSensor) {
        boolean eliminado = sensorService.delete(idSensor);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
