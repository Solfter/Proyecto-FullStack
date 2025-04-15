package cl.alcoholicos.gestorestacionamiento.sensor.controller;

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

import cl.alcoholicos.gestorestacionamiento.sensor.dto.SensorDTO;
import cl.alcoholicos.gestorestacionamiento.sensor.service.impl.SensorService;

@RequestMapping("/sensor")
@RestController
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public ResponseEntity<List<SensorDTO>> getAll() {
        List<SensorDTO> sensores = sensorService.getAll();
        if (sensores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sensores);
    }

    @PostMapping
    public ResponseEntity<SensorDTO> insert(@RequestBody SensorDTO sensor) {
        SensorDTO nuevoSensor = sensorService.insert(sensor);
        return ResponseEntity.ok(nuevoSensor);
    }

    @PutMapping("/{idSensor}")
    public ResponseEntity<SensorDTO> update(@PathVariable Integer idSensor, @RequestBody SensorDTO sensor) {
        SensorDTO sensorExistente = sensorService.getById(idSensor);
        if (sensorExistente == null) {
            return ResponseEntity.notFound().build();
        }
        SensorDTO sensorActualizado = sensorService.update(idSensor, sensor);
        return ResponseEntity.ok(sensorActualizado);
    }

    @DeleteMapping("/{idSensor}")
    public ResponseEntity<SensorDTO> delete(@PathVariable Integer idSensor) {

        SensorDTO sensor = sensorService.getById(idSensor);
        if (sensor == null) {
            return ResponseEntity.notFound().build();
        }

        SensorDTO sensorBorrado = sensorService.delete(idSensor);
        if (sensorBorrado == null) {
            return ResponseEntity.ok(sensor);
        }

        return ResponseEntity.badRequest().build();
    }
}
