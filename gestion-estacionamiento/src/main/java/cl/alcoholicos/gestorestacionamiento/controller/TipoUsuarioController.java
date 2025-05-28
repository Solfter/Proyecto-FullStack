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

import cl.alcoholicos.gestorestacionamiento.dto.TipoUsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.service.impl.TipoUsuarioService;

@RequestMapping("/tipousuario")
@RestController
public class TipoUsuarioController {
    
    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @GetMapping
    public ResponseEntity<List<TipoUsuarioResponseDTO>> getAll() {
        List<TipoUsuarioResponseDTO> tipoUsuarios = tipoUsuarioService.getAll();
        if (tipoUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoUsuarios);  
    }

    @PostMapping
    public ResponseEntity<TipoUsuarioResponseDTO> insert(@RequestBody TipoUsuarioResponseDTO tipoUsuario) {
        TipoUsuarioResponseDTO nuevoTipoUsuario = tipoUsuarioService.insert(tipoUsuario);
        return ResponseEntity.ok(nuevoTipoUsuario);
    }

    @PutMapping("/{idTipoUsuario}")
    public ResponseEntity<TipoUsuarioResponseDTO> update(@PathVariable Integer idTipoUsuario, @RequestBody TipoUsuarioResponseDTO tipoUsuario) {
        TipoUsuarioResponseDTO tipoUsuarioExistente = tipoUsuarioService.getById(idTipoUsuario);
        if (tipoUsuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }
        TipoUsuarioResponseDTO tipoUsurioActualizado = tipoUsuarioService.update(idTipoUsuario, tipoUsuario);
        return ResponseEntity.ok(tipoUsurioActualizado);

    }

    @DeleteMapping("/{idTipoUsuario}")
    public ResponseEntity<Void> delete (@PathVariable Integer idTipoUsuario) {
        boolean eliminado = tipoUsuarioService.delete(idTipoUsuario);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
