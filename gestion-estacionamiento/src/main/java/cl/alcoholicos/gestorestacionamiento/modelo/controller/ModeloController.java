package cl.alcoholicos.gestorestacionamiento.modelo.controller;

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

import cl.alcoholicos.gestorestacionamiento.modelo.dto.ModeloDTO;
import cl.alcoholicos.gestorestacionamiento.modelo.service.impl.ModeloService;

@RequestMapping("/modelo")
@RestController
public class ModeloController {
    
    @Autowired
    private ModeloService modeloService;

    @GetMapping
    public ResponseEntity<List<ModeloDTO>> getAll() {
        List<ModeloDTO> modelos = modeloService.getAll();
        if (modelos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(modelos);  
    }

    @PostMapping
    public ResponseEntity<ModeloDTO> insert(@RequestBody ModeloDTO modelo) {
        ModeloDTO nuevaMarca = modeloService.insert(modelo);
        return ResponseEntity.ok(nuevaMarca);
    }

    @PutMapping("/{idModelo}")
    public ResponseEntity<ModeloDTO> update(@PathVariable Integer idModelo, @RequestBody ModeloDTO modelo) {
        ModeloDTO modeloExistente = modeloService.getById(idModelo);
        if (modeloExistente == null) {
            return ResponseEntity.notFound().build();
        }
        ModeloDTO modeloActualizado = modeloService.update(idModelo, modelo);
        return ResponseEntity.ok(modeloActualizado);

    }

    @DeleteMapping("/{idModelo}")
    public ResponseEntity<ModeloDTO> delete (@PathVariable Integer idModelo) {
        ModeloDTO modelo = modeloService.getById(idModelo);
        if (modelo == null) {
            return ResponseEntity.notFound().build();
        }

        ModeloDTO modeloBorrado = modeloService.delete(idModelo);
        if (modeloBorrado == null) {
            return ResponseEntity.ok(modelo);
        }

        return ResponseEntity.badRequest().build();
    }
}
