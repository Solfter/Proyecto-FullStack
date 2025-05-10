package cl.alcoholicos.gestorestacionamiento.marca.controller;

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

import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.marca.entity.MarcaEntity;
import cl.alcoholicos.gestorestacionamiento.marca.service.impl.MarcaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<MarcaResponseDTO>> getAll() {
        List<MarcaResponseDTO> marcas = marcaService.getAll();
        if (marcas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marcas);  
    }

    @GetMapping("/{idMarca}")
    public ResponseEntity<MarcaResponseDTO> getById(@PathVariable int idMarca) {
        MarcaResponseDTO marca = marcaService.getById(idMarca);
        if (marca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marca);
    }

    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody MarcaCreateDTO marcaCreateDTO) {
        try {
            MarcaResponseDTO nuevaMarca = marcaService.insert(marcaCreateDTO);
            return ResponseEntity.ok(nuevaMarca);   
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.badRequest().body("Error: Datos incompletos - " + e.getRootCause().getMessage());
            }
    }

    @PutMapping("/{idMarca}")
    public ResponseEntity<MarcaResponseDTO> update(@PathVariable int idMarca, @RequestBody MarcaUpdateDTO MarcaUpdateDTO) {
        MarcaResponseDTO marcaActualizada = marcaService.update(idMarca, MarcaUpdateDTO);
        if (marcaActualizada == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marcaActualizada);
    }

    @DeleteMapping("/{idMarca}")
    public ResponseEntity<MarcaEntity> delete (@PathVariable int idMarca) {
        boolean eliminado = marcaService.delete(idMarca);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
