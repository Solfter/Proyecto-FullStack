package cl.alcoholicos.gestorestacionamiento.tipousuario.controller;

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

import cl.alcoholicos.gestorestacionamiento.tipousuario.entity.TipoUsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.tipousuario.service.impl.TipoUsuarioService;

@RequestMapping("/tipousuario")
@RestController
public class TipoUsuarioController {
    
    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @GetMapping
    public ResponseEntity<List<TipoUsuarioEntity>> getAll() {
        List<TipoUsuarioEntity> tipoUsuarios = tipoUsuarioService.getAll();
        if (tipoUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoUsuarios);  
    }

    @PostMapping
    public ResponseEntity<TipoUsuarioEntity> insert(@RequestBody TipoUsuarioEntity tipoUsuario) {
        TipoUsuarioEntity nuevoTipoUsuario = tipoUsuarioService.insert(tipoUsuario);
        return ResponseEntity.ok(nuevoTipoUsuario);
    }

    @PutMapping("/{idTipoUsuario}")
    public ResponseEntity<TipoUsuarioEntity> update(@PathVariable Integer idTipoUsuario, @RequestBody TipoUsuarioEntity tipoUsuario) {
        TipoUsuarioEntity tipoUsuarioExistente = tipoUsuarioService.getById(idTipoUsuario);
        if (tipoUsuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }
        TipoUsuarioEntity tipoUsurioActualizado = tipoUsuarioService.update(idTipoUsuario, tipoUsuario);
        return ResponseEntity.ok(tipoUsurioActualizado);

    }

    @DeleteMapping("/{idTipoUsuario}")
    public ResponseEntity<TipoUsuarioEntity> delete (@PathVariable Integer idTipoUsuario) {
        TipoUsuarioEntity tipoUsuario = tipoUsuarioService.getById(idTipoUsuario);
        if (tipoUsuario == null) {
            return ResponseEntity.notFound().build();
        }

        TipoUsuarioEntity tipoUsuarioBorrado = tipoUsuarioService.delete(idTipoUsuario);
        if (tipoUsuarioBorrado == null) {
            return ResponseEntity.ok(tipoUsuario);
        }

        return ResponseEntity.badRequest().build();
    }
}
