
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "API para la gestión de usuarios del sistema")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    
    @GetMapping
    @Operation(
        summary = "Obtener todos los usuarios",
        description = "Retorna una lista con todos los usuarios registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de usuarios obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UsuarioResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay usuarios registrados",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
        List<UsuarioResponseDTO> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{rut}")
    @Operation(
        summary = "Obtener usuario por RUT",
        description = "Retorna los datos de un usuario específico usando su RUT"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuario encontrado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UsuarioResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<UsuarioResponseDTO> getById(
        @Parameter(description = "RUT del usuario a buscar", required = true, example = "12345678")
        @PathVariable Integer rut) {
        UsuarioResponseDTO usuario = usuarioService.getById(rut);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @SuppressWarnings("null")
    @PostMapping
    @Operation(
        summary = "Crear un nuevo usuario",
        description = "Registra un nuevo usuario en el sistema con los datos proporcionados"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuario creado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UsuarioResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos o incompletos",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        ),
        @ApiResponse(
            responseCode = "422",
            description = "Error de validación en los datos de entrada",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<?> insert(
        @Parameter(description = "Datos del usuario a crear", required = true)
        @Valid @RequestBody UsuarioCreateDTO createDTO) {
        try {
            UsuarioResponseDTO nuevoUsuario = usuarioService.insert(createDTO);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error: Datos incompletos - " + e.getRootCause().getMessage());
        }
    }

    @PutMapping("/{rut}")
    @Operation(
        summary = "Actualizar usuario",
        description = "Actualiza los datos de un usuario existente identificado por su RUT"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuario actualizado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UsuarioResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<UsuarioResponseDTO> update(
        @Parameter(description = "RUT del usuario a actualizar", required = true, example = "12345678")
        @PathVariable Integer rut,
        @Parameter(description = "Datos del usuario a actualizar", required = true)
        @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
        UsuarioResponseDTO usuarioActualizado = usuarioService.update(rut, usuarioUpdateDTO);
        if (usuarioActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{rut}")
    @Operation(
        summary = "Eliminar usuario",
        description = "Elimina un usuario del sistema usando su RUT"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Usuario eliminado exitosamente",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<Void> delete(
        @Parameter(description = "RUT del usuario a eliminar", required = true, example = "12345678")
        @PathVariable Integer rut) {
        boolean eliminado = usuarioService.delete(rut);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/validar")
    @Operation(
        summary = "Validar credenciales de usuario",
        description = "Valida las credenciales de un usuario (correo y contraseña) y retorna información básica si son correctas"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Credenciales válidas",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UsuarioBasicDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Credenciales inválidas",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class, example = "Credenciales Inválidas")
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<?> validarUsuario(
        @Parameter(description = "Credenciales de usuario (correo y contraseña)", required = true)
        @RequestBody LoginRequest loginRequest) {
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
