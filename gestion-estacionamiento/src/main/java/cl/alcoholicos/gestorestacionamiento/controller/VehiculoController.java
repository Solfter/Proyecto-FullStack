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

import cl.alcoholicos.gestorestacionamiento.dto.VehiculoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.VehiculoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.VehiculoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.service.impl.VehiculoService;
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
@RequestMapping("/vehiculos")
@RequiredArgsConstructor
@Tag(name = "Vehículos", description = "API para la gestión de vehículos del sistema")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    @GetMapping
    @Operation(
        summary = "Obtener todos los vehículos",
        description = "Retorna una lista con todos los vehículos registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de vehículos obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = VehiculoResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay vehículos registrados",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<List<VehiculoResponseDTO>> getAll() {
        List<VehiculoResponseDTO> vehiculos = vehiculoService.getAll();
        if (vehiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/{patente}")
    @Operation(
        summary = "Obtener vehículo por patente",
        description = "Retorna los datos de un vehículo específico usando su patente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Vehículo encontrado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = VehiculoResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Vehículo no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Patente inválida",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<VehiculoResponseDTO> getById(
        @Parameter(description = "Patente del vehículo a buscar", required = true, example = "ABC1234")
        @PathVariable String patente) {
        VehiculoResponseDTO vehiculo = vehiculoService.getById(patente);
        if (vehiculo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculo);
    }

    @SuppressWarnings("null")
    @PostMapping
    @Operation(
        summary = "Crear un nuevo vehículo",
        description = "Registra un nuevo vehículo en el sistema con los datos proporcionados"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Vehículo creado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = VehiculoResponseDTO.class)
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
            responseCode = "409",
            description = "Vehículo con esa patente ya existe",
            content = @Content
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
        @Parameter(description = "Datos del vehículo a crear", required = true)
        @Valid @RequestBody VehiculoCreateDTO vehiculoCreateDTO) {
        try {
            VehiculoResponseDTO nuevoVehiculo = vehiculoService.insert(vehiculoCreateDTO);
            return ResponseEntity.ok(nuevoVehiculo);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error: datos incompletos - " + e.getRootCause().getMessage());
        }
    }

    @PutMapping("/{patente}")
    @Operation(
        summary = "Actualizar vehículo",
        description = "Actualiza los datos de un vehículo existente identificado por su patente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Vehículo actualizado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = VehiculoResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Vehículo no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos o patente inválida",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<VehiculoResponseDTO> update(
        @Parameter(description = "Patente del vehículo a actualizar", required = true, example = "ABC1234")
        @PathVariable String patente,
        @Parameter(description = "Datos del vehículo a actualizar", required = true)
        @RequestBody VehiculoUpdateDTO vehiculoUpdateDTO) {
        VehiculoResponseDTO vehiculoActualizado = vehiculoService.update(patente, vehiculoUpdateDTO);
        if (vehiculoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculoActualizado);
    }

    @DeleteMapping("/{patente}")
    @Operation(
        summary = "Eliminar vehículo",
        description = "Elimina un vehículo del sistema usando su patente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Vehículo eliminado exitosamente",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Vehículo no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "No se puede eliminar el vehículo porque está siendo usado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<Void> delete(
        @Parameter(description = "Patente del vehículo a eliminar", required = true, example = "ABC1234")
        @PathVariable String patente) {
        boolean eliminado = vehiculoService.delete(patente);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}