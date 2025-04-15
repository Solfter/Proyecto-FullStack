package cl.alcoholicos.gestorestacionamiento.marca.controller;

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

import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaDTO;
import cl.alcoholicos.gestorestacionamiento.marca.service.impl.MarcaService;

@RestController
@RequestMapping("/marca")
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<MarcaDTO>> getAll() {
        List<MarcaDTO> marcas = marcaService.getAll();
        if (marcas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marcas);  
    }

    @PostMapping
    public ResponseEntity<MarcaDTO> insert(@RequestBody MarcaDTO marca) {
        MarcaDTO nuevaMarca = marcaService.insert(marca);
        return ResponseEntity.ok(nuevaMarca);
    }

    @PutMapping("/{idMarca}")
    public ResponseEntity<MarcaDTO> update(@PathVariable Integer idMarca, @RequestBody MarcaDTO marca) {
        MarcaDTO marcaExistente = marcaService.getById(idMarca);
        if (marcaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        MarcaDTO marcaActualizada = marcaService.update(idMarca, marcaExistente);
        return ResponseEntity.ok(marcaActualizada);

    }

    @DeleteMapping("/{idMarca}")
    public ResponseEntity<MarcaDTO> delete (@PathVariable Integer idMarca) {
        MarcaDTO marca = marcaService.getById(idMarca);
        if (marca == null) {
            return ResponseEntity.notFound().build();
        }

        MarcaDTO marcaBorrada = marcaService.delete(idMarca);
        if (marcaBorrada == null) {
            return ResponseEntity.ok(marca);
        }

        return ResponseEntity.badRequest().build();
    }
}
