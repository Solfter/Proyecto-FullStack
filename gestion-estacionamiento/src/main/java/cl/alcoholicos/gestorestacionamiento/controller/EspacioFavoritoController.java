package cl.alcoholicos.gestorestacionamiento.controller;

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

import cl.alcoholicos.gestorestacionamiento.entity.EspacioFavoritoEntity;
import cl.alcoholicos.gestorestacionamiento.service.impl.EspacioFavoritoService;

@RestController
@RequestMapping("/espaciofavorito")
public class EspacioFavoritoController {

    @Autowired EspacioFavoritoService espacioFavoritoService;

    @GetMapping
    public ResponseEntity<List<EspacioFavoritoEntity>> getAll() {
        List<EspacioFavoritoEntity> espaciosFavoritos = espacioFavoritoService.getAll();
        if (espaciosFavoritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(espaciosFavoritos);
    }

    @PostMapping
    public ResponseEntity<EspacioFavoritoEntity> insert(@RequestBody EspacioFavoritoEntity espacioFavorito) {
        EspacioFavoritoEntity nuevoEspacioFavorito = espacioFavoritoService.insert(espacioFavorito);
        return ResponseEntity.ok(nuevoEspacioFavorito);
    }

    @PutMapping("/{rut}-{id}")
    public ResponseEntity<EspacioFavoritoEntity> update(@PathVariable Integer rutUsuario, @PathVariable Integer idEstacionamiento, @RequestBody EspacioFavoritoEntity espacioFavorito) {
        EspacioFavoritoEntity espacioFavoritoExistente = espacioFavoritoService.getById(rutUsuario, idEstacionamiento);
        if (espacioFavoritoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        EspacioFavoritoEntity espacioFavoritoActualizado = espacioFavoritoService.update(rutUsuario, idEstacionamiento, espacioFavoritoExistente);
        return ResponseEntity.ok(espacioFavoritoActualizado);
    }

    @DeleteMapping("/{rut}-{id}")
    public ResponseEntity<EspacioFavoritoEntity> delete(@PathVariable Integer rutUsuario, @PathVariable Integer idEstacionamiento) {
        EspacioFavoritoEntity espacioFavorito = espacioFavoritoService.getById(rutUsuario, idEstacionamiento);
        if (espacioFavorito == null) {
            return ResponseEntity.notFound().build();
        }
        EspacioFavoritoEntity espacioFavoritoBorrado = espacioFavoritoService.delete(rutUsuario, idEstacionamiento);
        if (espacioFavoritoBorrado == null) {
            return ResponseEntity.ok(espacioFavorito);
        }
        return ResponseEntity.badRequest().build();
    }
}
