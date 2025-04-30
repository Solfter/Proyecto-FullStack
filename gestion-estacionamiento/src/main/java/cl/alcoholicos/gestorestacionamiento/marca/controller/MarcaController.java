package cl.alcoholicos.gestorestacionamiento.marca.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.marca.service.impl.MarcaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/marca")
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
    public ResponseEntity<MarcaResponseDTO> getById(@PathVariable Integer idMarca) {
        MarcaResponseDTO marca = marcaService.getById(idMarca);
        if (marca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marca);
    }
}
