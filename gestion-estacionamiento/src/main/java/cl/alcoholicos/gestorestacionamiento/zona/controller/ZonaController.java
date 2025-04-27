package cl.alcoholicos.gestorestacionamiento.zona.controller;

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

import cl.alcoholicos.gestorestacionamiento.zona.entity.ZonaEntity;
import cl.alcoholicos.gestorestacionamiento.zona.service.impl.ZonaService;

@RequestMapping("/zona")
@RestController
public class ZonaController {
    @Autowired
    private ZonaService zonaService;

    @GetMapping
    public ResponseEntity<List<ZonaEntity>> getAll() {
        List<ZonaEntity> zonas = zonaService.getAll();
        if (zonas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(zonas);  
    }

    @PostMapping
    public ResponseEntity<ZonaEntity> insert(@RequestBody ZonaEntity zona) {
        ZonaEntity nuevaZona = zonaService.insert(zona);
        return ResponseEntity.ok(nuevaZona);
    }

    @PutMapping("/{idZona}")
    public ResponseEntity<ZonaEntity> update(@PathVariable Character idZona, @RequestBody ZonaEntity zona) {
        ZonaEntity zonaExistente = zonaService.getById(idZona);
        if (zonaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        ZonaEntity zonaActualizada = zonaService.update(idZona, zona);
        return ResponseEntity.ok(zonaActualizada);

    }

    @DeleteMapping("/{idZona}")
    public ResponseEntity<ZonaEntity> delete (@PathVariable Character idZona) {
        ZonaEntity zona = zonaService.getById(idZona);
        if (zona == null) {
            return ResponseEntity.notFound().build();
        }

        ZonaEntity zonaBorrada = zonaService.delete(idZona);
        if (zonaBorrada == null) {
            return ResponseEntity.ok(zona);
        }

        return ResponseEntity.badRequest().build();
    }
}
