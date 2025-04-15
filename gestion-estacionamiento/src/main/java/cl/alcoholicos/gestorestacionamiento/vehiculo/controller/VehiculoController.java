package cl.alcoholicos.gestorestacionamiento.vehiculo.controller;

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

import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoDTO;
import cl.alcoholicos.gestorestacionamiento.vehiculo.service.impl.VehiculoService;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<List<VehiculoDTO>> getAll() {
        List<VehiculoDTO> vehiculos = vehiculoService.getAll();
        if (vehiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehiculos);
    }

    @PostMapping
    public ResponseEntity<VehiculoDTO> insert(@RequestBody VehiculoDTO vehiculo) {
        VehiculoDTO nuevoVehiculo = vehiculoService.insert(vehiculo);
        return ResponseEntity.ok(nuevoVehiculo);
    }

    @PutMapping("/{patente}")
    public ResponseEntity<VehiculoDTO> update(@PathVariable String patente, @RequestBody VehiculoDTO vehiculo) {
        VehiculoDTO vehiculoExistente = vehiculoService.getById(patente);
        if (vehiculoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        VehiculoDTO vehiculoActualizado = vehiculoService.update(patente, vehiculo);
        return ResponseEntity.ok(vehiculoActualizado);
    }

    @DeleteMapping("/{patente}")
    public ResponseEntity<VehiculoDTO> delete(@PathVariable String patente) {

        VehiculoDTO vehiculo = vehiculoService.getById(patente);
        if (vehiculo == null) {
            return ResponseEntity.notFound().build();
        }

        VehiculoDTO vehiculoBorrado = vehiculoService.delete(patente);
        if (vehiculoBorrado == null) {
            return ResponseEntity.ok(vehiculo);
        }

        return ResponseEntity.badRequest().build();
    }
}
