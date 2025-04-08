
package cl.estacionamiento.usuario.controller;

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

import cl.estacionamiento.usuario.dto.UsuarioDTO;
import cl.estacionamiento.usuario.service.impl.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAll() {
        List<UsuarioDTO> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> insert(@RequestBody UsuarioDTO usuario) {
        UsuarioDTO nuevoUsuario = usuarioService.insert(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PutMapping("/{rut}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Integer rut, @RequestBody UsuarioDTO usuario) {
        UsuarioDTO usuarioExistente = usuarioService.getById(rut);
        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }
        UsuarioDTO usuarioActualizado = usuarioService.update(rut, usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{rut}")
    public ResponseEntity<UsuarioDTO> delete(@PathVariable Integer rut) {
        UsuarioDTO usuario = usuarioService.getById(rut);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        UsuarioDTO usuarioBorrado = usuarioService.delete(rut);
        if (usuarioBorrado == null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.badRequest().build();
    }

}
