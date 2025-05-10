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

import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;
import cl.alcoholicos.gestorestacionamiento.service.impl.SensorService;

@RequestMapping("/sensor")
@RestController
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public ResponseEntity<List<SensorEntity>> getAll() {
        List<SensorEntity> sensores = sensorService.getAll();
        if (sensores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sensores);
    }

    @PostMapping
    public ResponseEntity<SensorEntity> insert(@RequestBody SensorEntity sensor) {
        SensorEntity nuevoSensor = sensorService.insert(sensor);
        return ResponseEntity.ok(nuevoSensor);
    }

    @PutMapping("/{idSensor}")
    public ResponseEntity<SensorEntity> update(@PathVariable Integer idSensor, @RequestBody SensorEntity sensor) {
        SensorEntity sensorExistente = sensorService.getById(idSensor);
        if (sensorExistente == null) {
            return ResponseEntity.notFound().build();
        }
        SensorEntity sensorActualizado = sensorService.update(idSensor, sensor);
        return ResponseEntity.ok(sensorActualizado);
    }

    @DeleteMapping("/{idSensor}")
    public ResponseEntity<SensorEntity> delete(@PathVariable Integer idSensor) {

        SensorEntity sensor = sensorService.getById(idSensor);
        if (sensor == null) {
            return ResponseEntity.notFound().build();
        }

        SensorEntity sensorBorrado = sensorService.delete(idSensor);
        if (sensorBorrado == null) {
            return ResponseEntity.ok(sensor);
        }

        return ResponseEntity.badRequest().build();
    }
}
