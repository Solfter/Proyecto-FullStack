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

import cl.alcoholicos.gestorestacionamiento.dto.ModeloCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ModeloResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.ModeloEntity;
import cl.alcoholicos.gestorestacionamiento.service.impl.ModeloService;

@RequestMapping("/modelo")
@RestController
public class ModeloController {
    
    @Autowired
    private ModeloService modeloService;

    @GetMapping
    public ResponseEntity<List<ModeloResponseDTO>> getAll() {
        List<ModeloResponseDTO> modelos = modeloService.getAll();
        if (modelos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(modelos);  
    }

    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody ModeloCreateDTO modelo) {
        try {
            ModeloResponseDTO nuevoModelo = modeloService.insert(modelo);
            return ResponseEntity.ok(nuevoModelo);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error: Datos incompletos - " + e.getRootCause().getMessage());
        }
    }

    @PutMapping("/{idModelo}")
    public ResponseEntity<ModeloResponseDTO> update(@PathVariable Integer idModelo, @RequestBody ModeloResponseDTO modelo) {
        ModeloResponseDTO modeloActualizado = modeloService.update(idModelo, modelo);
        if (modeloActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modeloActualizado);

    }

    @DeleteMapping("/{idModelo}")
    public ResponseEntity<ModeloEntity> delete (@PathVariable Integer idModelo) {
        boolean eliminado = modeloService.delete(idModelo);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
