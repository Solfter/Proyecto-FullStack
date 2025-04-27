
package cl.alcoholicos.gestorestacionamiento.usuario.controller;

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

import cl.alcoholicos.gestorestacionamiento.usuario.entity.UsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.usuario.service.impl.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> getAll() {
        List<UsuarioEntity> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> insert(@RequestBody UsuarioEntity usuario) {
        UsuarioEntity nuevoUsuario = usuarioService.insert(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PutMapping("/{rut}")
    public ResponseEntity<UsuarioEntity> update(@PathVariable Integer rut, @RequestBody UsuarioEntity usuario) {
        UsuarioEntity usuarioExistente = usuarioService.getById(rut);
        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }
        UsuarioEntity usuarioActualizado = usuarioService.update(rut, usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{rut}")
    public ResponseEntity<UsuarioEntity> delete(@PathVariable Integer rut) {
        UsuarioEntity usuario = usuarioService.getById(rut);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        UsuarioEntity usuarioBorrado = usuarioService.delete(rut);
        if (usuarioBorrado == null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.badRequest().build();
    }

}
