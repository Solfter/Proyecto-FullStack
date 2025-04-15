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

import cl.alcoholicos.gestorestacionamiento.tipousuario.dto.TipoUsuarioDTO;
import cl.alcoholicos.gestorestacionamiento.tipousuario.service.impl.TipoUsuarioService;

@RequestMapping("/tipousuario")
@RestController
public class TipoUsuarioController {
    
    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @GetMapping
    public ResponseEntity<List<TipoUsuarioDTO>> getAll() {
        List<TipoUsuarioDTO> tipoUsuarios = tipoUsuarioService.getAll();
        if (tipoUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoUsuarios);  
    }

    @PostMapping
    public ResponseEntity<TipoUsuarioDTO> insert(@RequestBody TipoUsuarioDTO tipoUsuario) {
        TipoUsuarioDTO nuevoTipoUsuario = tipoUsuarioService.insert(tipoUsuario);
        return ResponseEntity.ok(nuevoTipoUsuario);
    }

    @PutMapping("/{idTipoUsuario}")
    public ResponseEntity<TipoUsuarioDTO> update(@PathVariable Integer idTipoUsuario, @RequestBody TipoUsuarioDTO tipoUsuario) {
        TipoUsuarioDTO tipoUsuarioExistente = tipoUsuarioService.getById(idTipoUsuario);
        if (tipoUsuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }
        TipoUsuarioDTO tipoUsurioActualizado = tipoUsuarioService.update(idTipoUsuario, tipoUsuario);
        return ResponseEntity.ok(tipoUsurioActualizado);

    }

    @DeleteMapping("/{idTipoUsuario}")
    public ResponseEntity<TipoUsuarioDTO> delete (@PathVariable Integer idTipoUsuario) {
        TipoUsuarioDTO tipoUsuario = tipoUsuarioService.getById(idTipoUsuario);
        if (tipoUsuario == null) {
            return ResponseEntity.notFound().build();
        }

        TipoUsuarioDTO tipoUsuarioBorrado = tipoUsuarioService.delete(idTipoUsuario);
        if (tipoUsuarioBorrado == null) {
            return ResponseEntity.ok(tipoUsuarioBorrado);
        }

        return ResponseEntity.badRequest().build();
    }
}
