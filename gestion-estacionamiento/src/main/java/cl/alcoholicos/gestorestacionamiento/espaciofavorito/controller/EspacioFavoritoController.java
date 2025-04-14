package cl.alcoholicos.gestorestacionamiento.espaciofavorito.controller;

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

import cl.alcoholicos.gestorestacionamiento.espaciofavorito.dto.EspacioFavoritoDTO;
import cl.alcoholicos.gestorestacionamiento.espaciofavorito.service.impl.EspacioFavoritoService;

@RestController
@RequestMapping("/espaciofavorito")
public class EspacioFavoritoController {

    @Autowired EspacioFavoritoService espacioFavoritoService;

    @GetMapping
    public ResponseEntity<List<EspacioFavoritoDTO>> getAll() {
        List<EspacioFavoritoDTO> espaciosFavoritos = espacioFavoritoService.getAll();
        if (espaciosFavoritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(espaciosFavoritos);
    }

    @PostMapping
    public ResponseEntity<EspacioFavoritoDTO> insert(@RequestBody EspacioFavoritoDTO espacioFavorito) {
        EspacioFavoritoDTO nuevoEspacioFavorito = espacioFavoritoService.insert(espacioFavorito);
        return ResponseEntity.ok(nuevoEspacioFavorito);
    }

    @PutMapping("/{rut}-{id}")
    public ResponseEntity<EspacioFavoritoDTO> update(@PathVariable Integer rutUsuario, @PathVariable Integer idEstacionamiento, @RequestBody EspacioFavoritoDTO espacioFavorito) {
        EspacioFavoritoDTO espacioFavoritoExistente = espacioFavoritoService.getById(rutUsuario, idEstacionamiento);
        if (espacioFavoritoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        EspacioFavoritoDTO espacioFavoritoActualizado = espacioFavoritoService.update(rutUsuario, idEstacionamiento, espacioFavoritoExistente);
        return ResponseEntity.ok(espacioFavoritoActualizado);
    }

    @DeleteMapping("/{rut}-{id}")
    public ResponseEntity<EspacioFavoritoDTO> delete(@PathVariable Integer rutUsuario, @PathVariable Integer idEstacionamiento) {
        EspacioFavoritoDTO espacioFavorito = espacioFavoritoService.getById(rutUsuario, idEstacionamiento);
        if (espacioFavorito == null) {
            return ResponseEntity.notFound().build();
        }
        EspacioFavoritoDTO espacioFavoritoBorrado = espacioFavoritoService.delete(rutUsuario, idEstacionamiento);
        if (espacioFavoritoBorrado == null) {
            return ResponseEntity.ok(espacioFavorito);
        }
        return ResponseEntity.badRequest().build();
    }
}
