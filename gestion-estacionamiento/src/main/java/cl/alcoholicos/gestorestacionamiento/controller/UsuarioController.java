
package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.dto.LoginRequest;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioBasicDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.service.impl.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
        List<UsuarioResponseDTO> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{rut}")
    public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Integer rut) {
        UsuarioResponseDTO usuario = usuarioService.getById(rut);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody UsuarioCreateDTO createDTO) {
        try {
            UsuarioResponseDTO nuevoUsuario = usuarioService.insert(createDTO);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error: Datos incompletos - " + e.getRootCause().getMessage());
        }
    }

    @PutMapping("/{rut}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Integer rut, @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
        UsuarioResponseDTO usuarioActualizado = usuarioService.update(rut, usuarioUpdateDTO);
        if (usuarioActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{rut}")
    public ResponseEntity<Void> delete(@PathVariable Integer rut) {
        boolean eliminado = usuarioService.delete(rut);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/validar")
    public ResponseEntity<?> validarUsuario(@RequestBody LoginRequest loginRequest) {
        // Buscar Usuario por correo
        UsuarioResponseDTO usuario = usuarioService.findByCorreo(loginRequest.getCorreo());
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        
        if (passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
            UsuarioBasicDTO usuarioBasic = new UsuarioBasicDTO();
            usuarioBasic.setRut(usuario.getRut());
            usuarioBasic.setCorreo(usuario.getCorreo());
            usuarioBasic.setPrimerNombre(usuario.getPrimerNombre());
            return ResponseEntity.ok(usuarioBasic);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales Inválidas");
    }

}
