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

import cl.alcoholicos.gestorestacionamiento.dto.SensorCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.SensorResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;
import cl.alcoholicos.gestorestacionamiento.service.impl.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/sensor")
@RestController
@Tag(name = "Sensores", description = "API para la gestión de sensores del sistema")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    @Operation(
        summary = "Obtener todos los sensores",
        description = "Retorna una lista con todos los sensores registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de sensores obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SensorResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay sensores registrados",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<List<SensorResponseDTO>> getAll() {
        List<SensorResponseDTO> sensores = sensorService.getAll();
        if (sensores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sensores);
    }

    @GetMapping("/{idSensor}")
    @Operation(
        summary = "Obtener sensor por ID",
        description = "Retorna los datos de un sensor específico usando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Sensor encontrado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SensorResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Sensor no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "ID inválido",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<SensorResponseDTO> getById(
        @Parameter(description = "ID del sensor a buscar", required = true, example = "1")
        @PathVariable Integer idSensor) {
        SensorResponseDTO sensor = sensorService.getById(idSensor);
        if (sensor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sensor);
    }

    @SuppressWarnings("null")
    @PostMapping
    @Operation(
        summary = "Crear un nuevo sensor",
        description = "Registra un nuevo sensor en el sistema con los datos proporcionados"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Sensor creado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SensorResponseDTO.class)
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
        @Parameter(description = "Datos del sensor a crear", required = true)
        @RequestBody SensorCreateDTO createDTO) {
        try{
            SensorResponseDTO nuevoSensor = sensorService.insert(createDTO);
            return ResponseEntity.ok(nuevoSensor);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error: Datos incompletos - " + e.getRootCause().getMessage());
        }
    }

    @PutMapping("/{idSensor}")
    @Operation(
        summary = "Actualizar sensor",
        description = "Actualiza los datos de un sensor existente identificado por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Sensor actualizado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SensorEntity.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Sensor no encontrado",
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
    public ResponseEntity<SensorEntity> update(
        @Parameter(description = "ID del sensor a actualizar", required = true, example = "1")
        @PathVariable Integer idSensor, 
        @Parameter(description = "Datos del sensor a actualizar", required = true)
        @RequestBody SensorEntity sensor) {
        /*SensorEntity sensorExistente = sensorService.getById(idSensor);
        if (sensorExistente == null) {
            return ResponseEntity.notFound().build();
        }
        SensorEntity sensorActualizado = sensorService.update(idSensor, sensor);
        return ResponseEntity.ok(sensorActualizado);*/
        return null;
    }

    @DeleteMapping("/{idSensor}")
    @Operation(
        summary = "Eliminar sensor",
        description = "Elimina un sensor del sistema usando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Sensor eliminado exitosamente",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Sensor no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "No se puede eliminar el sensor porque está siendo usado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID del sensor a eliminar", required = true, example = "1")
        @PathVariable Integer idSensor) {
        boolean eliminado = sensorService.delete(idSensor);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
