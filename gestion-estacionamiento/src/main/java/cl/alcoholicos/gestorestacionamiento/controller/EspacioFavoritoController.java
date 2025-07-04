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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/espaciofavorito")
@Tag(name = "Espacios Favoritos", description = "API para la gestión de espacios favoritos de los usuarios")
public class EspacioFavoritoController {

    @Autowired EspacioFavoritoService espacioFavoritoService;

    @GetMapping
    @Operation(
        summary = "Obtener todos los espacios favoritos",
        description = "Retorna una lista con todos los espacios favoritos registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de espacios favoritos obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EspacioFavoritoEntity.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay espacios favoritos registrados",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<List<EspacioFavoritoEntity>> getAll() {
        List<EspacioFavoritoEntity> espaciosFavoritos = espacioFavoritoService.getAll();
        if (espaciosFavoritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(espaciosFavoritos);
    }

    @PostMapping
    @Operation(
        summary = "Crear un nuevo espacio favorito",
        description = "Registra un nuevo espacio favorito en el sistema con los datos proporcionados"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Espacio favorito creado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EspacioFavoritoEntity.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos o incompletos",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<EspacioFavoritoEntity> insert(
        @Parameter(description = "Datos del espacio favorito a crear", required = true)
        @RequestBody EspacioFavoritoEntity espacioFavorito) {
        EspacioFavoritoEntity nuevoEspacioFavorito = espacioFavoritoService.insert(espacioFavorito);
        return ResponseEntity.ok(nuevoEspacioFavorito);
    }

    @PutMapping("/{rut}-{id}")
    @Operation(
        summary = "Actualizar espacio favorito",
        description = "Actualiza los datos de un espacio favorito existente identificado por RUT de usuario e ID de estacionamiento"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Espacio favorito actualizado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EspacioFavoritoEntity.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Espacio favorito no encontrado",
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
    public ResponseEntity<EspacioFavoritoEntity> update(
        @Parameter(description = "RUT del usuario", required = true, example = "12345678")
        @PathVariable Integer rutUsuario, 
        @Parameter(description = "ID del estacionamiento", required = true, example = "1")
        @PathVariable Integer idEstacionamiento, 
        @Parameter(description = "Datos del espacio favorito a actualizar", required = true)
        @RequestBody EspacioFavoritoEntity espacioFavorito) {
        EspacioFavoritoEntity espacioFavoritoExistente = espacioFavoritoService.getById(rutUsuario, idEstacionamiento);
        if (espacioFavoritoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        EspacioFavoritoEntity espacioFavoritoActualizado = espacioFavoritoService.update(rutUsuario, idEstacionamiento, espacioFavoritoExistente);
        return ResponseEntity.ok(espacioFavoritoActualizado);
    }

    @DeleteMapping("/{rut}-{id}")
    @Operation(
        summary = "Eliminar espacio favorito",
        description = "Elimina un espacio favorito del sistema usando el RUT del usuario y el ID del estacionamiento"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Espacio favorito eliminado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EspacioFavoritoEntity.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Espacio favorito no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "No se puede eliminar el espacio favorito porque está siendo usado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<EspacioFavoritoEntity> delete(
        @Parameter(description = "RUT del usuario", required = true, example = "12345678")
        @PathVariable Integer rutUsuario, 
        @Parameter(description = "ID del estacionamiento", required = true, example = "1")
        @PathVariable Integer idEstacionamiento) {
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
