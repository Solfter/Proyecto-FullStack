package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoUsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.service.impl.TipoEstadoReservaService;
import cl.alcoholicos.gestorestacionamiento.service.impl.TipoUsuarioService;

@RequestMapping("/ter")
@RestController
@Tag(name = "Tipos de Usuario", description = "API para la gestión de tipos de usuario del sistema")
public class TipoUsuarioControllerCopy {

    @Autowired
    private TipoEstadoReservaService tipoEstadoReservaService;
    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @PostMapping
    public ResponseEntity<TipoEstadoReservaResponseDTO> insert(
            @Parameter(description = "Datos del tipo de usuario a crear", required = true) @RequestBody TipoEstadoReservaCreateDTO tipoUsuario) {
        TipoEstadoReservaResponseDTO nuevoTipoUsuario = tipoEstadoReservaService.insert(tipoUsuario);
        return ResponseEntity.ok(nuevoTipoUsuario);
    }

    @PutMapping("/{idTipoUsuario}")
    @Operation(summary = "Actualizar tipo de usuario", description = "Actualiza los datos de un tipo de usuario existente identificado por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuario actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoUsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de usuario no encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    public ResponseEntity<TipoUsuarioResponseDTO> update(
            @Parameter(description = "ID del tipo de usuario a actualizar", required = true, example = "1") @PathVariable Integer idTipoUsuario,
            @Parameter(description = "Datos del tipo de usuario a actualizar", required = true) @RequestBody TipoUsuarioResponseDTO tipoUsuario) {
        TipoUsuarioResponseDTO tipoUsuarioExistente = tipoUsuarioService.getById(idTipoUsuario);
        if (tipoUsuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }
        TipoUsuarioResponseDTO tipoUsuarioActualizado = tipoUsuarioService.update(idTipoUsuario, tipoUsuario);
        return ResponseEntity.ok(tipoUsuarioActualizado);
    }

    @DeleteMapping("/{idTipoUsuario}")
    @Operation(summary = "Eliminar tipo de usuario", description = "Elimina un tipo de usuario del sistema usando su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tipo de usuario eliminado exitosamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Tipo de usuario no encontrado", content = @Content),
            @ApiResponse(responseCode = "409", description = "No se puede eliminar el tipo de usuario porque está siendo usado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID del tipo de usuario a eliminar", required = true, example = "1") @PathVariable Integer idTipoUsuario) {
        boolean eliminado = tipoUsuarioService.delete(idTipoUsuario);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idTipoUsuario}")
    @Operation(summary = "Obtener tipo de usuario por ID", description = "Retorna los datos de un tipo de usuario específico usando su ID")
    public ResponseEntity<TipoUsuarioResponseDTO> getById(
            @Parameter(description = "ID del tipo de usuario", required = true, example = "1") @PathVariable Integer idTipoUsuario) {
        TipoUsuarioResponseDTO tipoUsuario = tipoUsuarioService.getById(idTipoUsuario);
        if (tipoUsuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tipoUsuario);
    }
}