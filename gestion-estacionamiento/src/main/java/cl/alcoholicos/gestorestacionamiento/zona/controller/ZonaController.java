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

import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaDTO;
import cl.alcoholicos.gestorestacionamiento.zona.service.impl.ZonaService;

@RequestMapping("/zona")
@RestController
public class ZonaController {
    @Autowired
    private ZonaService zonaService;

    @GetMapping
    public ResponseEntity<List<ZonaDTO>> getAll() {
        List<ZonaDTO> zonas = zonaService.getAll();
        if (zonas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(zonas);  
    }

    @PostMapping
    public ResponseEntity<ZonaDTO> insert(@RequestBody ZonaDTO zona) {
        ZonaDTO nuevaZona = zonaService.insert(zona);
        return ResponseEntity.ok(nuevaZona);
    }

    @PutMapping("/{idZona}")
    public ResponseEntity<ZonaDTO> update(@PathVariable Character idZona, @RequestBody ZonaDTO zona) {
        ZonaDTO zonaExistente = zonaService.getById(idZona);
        if (zonaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        ZonaDTO zonaActualizada = zonaService.update(idZona, zonaExistente);
        return ResponseEntity.ok(zonaActualizada);

    }

    @DeleteMapping("/{idZona}")
    public ResponseEntity<ZonaDTO> delete (@PathVariable Character idZona) {
        ZonaDTO zona = zonaService.getById(idZona);
        if (zona == null) {
            return ResponseEntity.notFound().build();
        }

        ZonaDTO zonaBorrada = zonaService.delete(idZona);
        if (zonaBorrada == null) {
            return ResponseEntity.ok(zonaBorrada);
        }

        return ResponseEntity.badRequest().build();
    }
}
