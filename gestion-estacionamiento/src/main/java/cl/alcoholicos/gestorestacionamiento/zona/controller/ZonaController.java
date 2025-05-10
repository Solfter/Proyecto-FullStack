package cl.alcoholicos.gestorestacionamiento.zona.controller;

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

import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.zona.entity.ZonaEntity;
import cl.alcoholicos.gestorestacionamiento.zona.service.impl.ZonaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/zona")
@RestController
@RequiredArgsConstructor
public class ZonaController {
    
    private final ZonaService zonaService;

    @GetMapping
    public ResponseEntity<List<ZonaResponseDTO>> getAll() {
        List<ZonaResponseDTO> zonas = zonaService.getAll();
        if (zonas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(zonas);  
    }

    @GetMapping("/{idZona}")
    public ResponseEntity<ZonaResponseDTO> getById(@PathVariable String idZona) {
        ZonaResponseDTO zona = zonaService.getById(idZona);
        if (zona == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(zona);
    }
    
    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody ZonaCreateDTO zonaCreateDTO) {
        try {
            ZonaResponseDTO nuevaZona = zonaService.insert(zonaCreateDTO);
            return ResponseEntity.ok(nuevaZona);   
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.badRequest().body("Error: Datos incompletos - " + e.getRootCause().getMessage());
            }
    }

    @PutMapping("/{idZona}")
    public ResponseEntity<ZonaResponseDTO> update(@PathVariable String idZona, @RequestBody ZonaUpdateDTO zonaUpdateDTO) {
        ZonaResponseDTO zonaActualizada = zonaService.update(idZona, zonaUpdateDTO);
        if (zonaActualizada == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(zonaActualizada);
    }

    @DeleteMapping("/{idZona}")
    public ResponseEntity<ZonaEntity> delete (@PathVariable String idZona) {
        boolean eliminado = zonaService.delete(idZona);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
