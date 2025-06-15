package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.List;

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

import cl.alcoholicos.gestorestacionamiento.dto.MarcaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.MarcaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.MarcaUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.MarcaEntity;
import cl.alcoholicos.gestorestacionamiento.service.impl.MarcaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/marcas")
@RequiredArgsConstructor
@Tag(name = "Marcas", description = "API para la gestión de marcas de vehículos")
public class MarcaController {

    private final MarcaService marcaService;

    @Operation(
        summary = "Obtener todas las marcas",
        description = "Retorna una lista con todas las marcas de vehículos registradas en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de marcas obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MarcaResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No se encontraron marcas registradas",
            content = @Content
        )
    })
    @GetMapping
    public ResponseEntity<List<MarcaResponseDTO>> getAll() {
        List<MarcaResponseDTO> marcas = marcaService.getAll();
        if (marcas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marcas);  
    }

    @Operation(
        summary = "Obtener marca por ID",
        description = "Obtiene una marca específica mediante su ID único"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Marca encontrada exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MarcaResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Marca no encontrada con el ID proporcionado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "ID de marca inválido",
            content = @Content
        )
    })
    @GetMapping("/{idMarca}")
    public ResponseEntity<MarcaResponseDTO> getById(
        @Parameter(description = "ID único de la marca", required = true, example = "1")
        @PathVariable int idMarca
    ) {
        MarcaResponseDTO marca = marcaService.getById(idMarca);
        if (marca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marca);
    }

    @Operation(
        summary = "Crear nueva marca",
        description = "Crea una nueva marca de vehículo en el sistema. Los datos son validados automáticamente."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Marca creada exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MarcaResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos o incompletos. Puede incluir errores de validación o violaciones de integridad",
            content = @Content(
                mediaType = "text/plain",
                schema = @Schema(
                    type = "string", 
                    example = "Error: Datos incompletos - Duplicate entry 'Toyota' for key 'nombre'"
                )
            )
        ),
        @ApiResponse(
            responseCode = "422",
            description = "Errores de validación en los campos de entrada",
            content = @Content
        )
    })
    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<?> insert(
        @Parameter(description = "Datos de la marca a crear", required = true)
        @Valid @RequestBody MarcaCreateDTO marcaCreateDTO
    ) {
        try {
            MarcaResponseDTO nuevaMarca = marcaService.insert(marcaCreateDTO);
            return ResponseEntity.ok(nuevaMarca);   
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error: Datos incompletos - " + e.getRootCause().getMessage());
        }
    }

    @Operation(
        summary = "Actualizar marca",
        description = "Actualiza los datos de una marca existente mediante su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Marca actualizada exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MarcaResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Marca no encontrada con el ID proporcionado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada inválidos o errores de integridad",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "422",
            description = "Errores de validación en los campos de entrada",
            content = @Content
        )
    })
    @PutMapping("/{idMarca}")
    public ResponseEntity<MarcaResponseDTO> update(
        @Parameter(description = "ID único de la marca a actualizar", required = true, example = "1")
        @PathVariable int idMarca,
        
        @Parameter(description = "Datos actualizados de la marca", required = true)
        @RequestBody MarcaUpdateDTO marcaUpdateDTO
    ) {
        MarcaResponseDTO marcaActualizada = marcaService.update(idMarca, marcaUpdateDTO);
        if (marcaActualizada == null) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marcaActualizada);
    }

    @Operation(
        summary = "Eliminar marca",
        description = "Elimina una marca del sistema mediante su ID. La marca no puede ser eliminada si está siendo utilizada por modelos de vehículos."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Marca eliminada exitosamente",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Marca no encontrada con el ID proporcionado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "No se puede eliminar la marca porque está siendo utilizada por otros registros",
            content = @Content
        )
    })
    @DeleteMapping("/{idMarca}")
    public ResponseEntity<MarcaEntity> delete(
        @Parameter(description = "ID único de la marca a eliminar", required = true, example = "1")
        @PathVariable int idMarca
    ) {
        boolean eliminado = marcaService.delete(idMarca);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}