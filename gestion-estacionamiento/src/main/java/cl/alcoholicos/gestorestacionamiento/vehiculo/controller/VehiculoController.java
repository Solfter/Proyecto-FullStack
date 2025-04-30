package cl.alcoholicos.gestorestacionamiento.vehiculo.controller;

import java.util.List;

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

import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.vehiculo.entity.VehiculoEntity;
import cl.alcoholicos.gestorestacionamiento.vehiculo.service.impl.VehiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {

    private final VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<List<VehiculoResponseDTO>> getAll() {
        List<VehiculoResponseDTO> vehiculos = vehiculoService.getAll();
        if (vehiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/{patente}")
    public ResponseEntity<VehiculoResponseDTO> getById(@PathVariable String patente) {
        VehiculoResponseDTO vehiculo = vehiculoService.getById(patente);
        if (vehiculo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculo);
    }

    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody VehiculoCreateDTO vehiculoCreateDTO) {
        try {
            VehiculoResponseDTO nuevoVehiculo = vehiculoService.insert(vehiculoCreateDTO);
            return ResponseEntity.ok(nuevoVehiculo);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error: datos incompletos - " + e.getRootCause().getMessage());
        }
    }

    @PutMapping("/{patente}")
    public ResponseEntity<VehiculoResponseDTO> update(@PathVariable String patente, @RequestBody VehiculoUpdateDTO vehiculoUpdateDTO) {
        VehiculoResponseDTO vehiculoActualizado = vehiculoService.update(patente, vehiculoUpdateDTO);
        if (vehiculoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculoActualizado);
    }

    @DeleteMapping("/{patente}")
    public ResponseEntity<VehiculoEntity> delete(@PathVariable String patente) {

        boolean eliminado = vehiculoService.delete(patente);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
