package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.dto.ModeloCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ModeloResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.ModeloEntity;
import cl.alcoholicos.gestorestacionamiento.service.impl.ModeloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/modelo")
@RestController
@Tag(name = "Modelos", description = "API para la gestión de modelos de vehículos")
public class ModeloController {
    
    @Autowired
    private ModeloService modeloService;

    @Operation(
        summary = "Obtener todos los modelos",
        description = "Retorna una lista con todos los modelos de vehículos registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de modelos obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ModeloResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No se encontraron modelos registrados",
            content = @Content
        )
    })
    @GetMapping
    public ResponseEntity<List<ModeloResponseDTO>> getAll() {
        List<ModeloResponseDTO> modelos = modeloService.getAll();
        if (modelos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(modelos);  
    }

    @Operation(
        summary = "Crear nuevo modelo",
        description = "Crea un nuevo modelo de vehículo en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Modelo creado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ModeloResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos o incompletos. Error de integridad de datos",
            content = @Content(
                mediaType = "text/plain",
                schema = @Schema(type = "string", example = "Error: Datos incompletos - Duplicate entry")
            )
        )
    })
    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<?> insert(
        @Parameter(description = "Datos del modelo a crear", required = true)
        @RequestBody ModeloCreateDTO modelo
    ) {
        try {
            ModeloResponseDTO nuevoModelo = modeloService.insert(modelo);
            return ResponseEntity.ok(nuevoModelo);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error: Datos incompletos - " + e.getRootCause().getMessage());
        }
    }

    @Operation(
        summary = "Actualizar modelo",
        description = "Actualiza los datos de un modelo existente mediante su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Modelo actualizado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ModeloResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Modelo no encontrado con el ID proporcionado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada inválidos",
            content = @Content
        )
    })
    @PutMapping("/{idModelo}")
    public ResponseEntity<ModeloResponseDTO> update(
        @Parameter(description = "ID único del modelo a actualizar", required = true, example = "1")
        @PathVariable Integer idModelo,
        
        @Parameter(description = "Datos actualizados del modelo", required = true)
        @RequestBody ModeloResponseDTO modelo
    ) {
        ModeloResponseDTO modeloActualizado = modeloService.update(idModelo, modelo);
        if (modeloActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modeloActualizado);
    }

    @Operation(
        summary = "Eliminar modelo",
        description = "Elimina un modelo del sistema mediante su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Modelo eliminado exitosamente",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Modelo no encontrado con el ID proporcionado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "No se puede eliminar el modelo porque está siendo utilizado por otros registros",
            content = @Content
        )
    })
    @DeleteMapping("/{idModelo}")
    public ResponseEntity<ModeloEntity> delete(
        @Parameter(description = "ID único del modelo a eliminar", required = true, example = "1")
        @PathVariable Integer idModelo
    ) {
        boolean eliminado = modeloService.delete(idModelo);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}