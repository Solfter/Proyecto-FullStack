package cl.alcoholicos.gestorestacionamiento.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cl.alcoholicos.gestorestacionamiento.config.JwtTokenUtil;
import cl.alcoholicos.gestorestacionamiento.dto.MessageResponse;
import cl.alcoholicos.gestorestacionamiento.dto.VehiculoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.VehiculoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.VehiculoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.exception.ResourceNotFoundException;
import cl.alcoholicos.gestorestacionamiento.exception.ServiceUnavailableException;
import cl.alcoholicos.gestorestacionamiento.service.impl.VehiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
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

    private static final Logger logger = LoggerFactory.getLogger(ReservaController.class);
    private final VehiculoService vehiculoService;
    private final JwtTokenUtil jwtTokenUtil;

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

    @GetMapping("/usuario")
    public ResponseEntity<?> getAllByUsuario(@RequestHeader("Authorization") String authHeader) {
        
        try {
            // Extraer usuario del token
            Integer rutUsuario = extractUserFromToken(authHeader);
            
            List<VehiculoResponseDTO> vehiculos = vehiculoService.getAllByUsuario(rutUsuario);

            return ResponseEntity.ok(vehiculos != null ? vehiculos : new ArrayList<>());

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error: Datos incompletos - " + e.getRootCause().getMessage());

        } catch (ExpiredJwtException e) {
            logger.error("JWT expirado: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Token expirado, por favor inicie sesión nuevamente"));

        } catch (UnsupportedJwtException e) {
            logger.error("JWT no soportado: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Formato de token no soportado"));

        } catch (MalformedJwtException e) {
            logger.error("JWT malformado: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Token inválido"));

        } catch (SignatureException e) {
            logger.error("Error en firma del JWT: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Token con firma inválida"));

        } catch (ResourceNotFoundException e) {
            logger.error("AuthController: Usuario no encontrado: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse(e.getMessage()));

        } catch (ServiceUnavailableException e) {
            logger.error("Servicio no disponible: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new MessageResponse("Servicio temporalmente no disponible"));

        } catch (SecurityException e) {
            logger.error("Error de seguridad: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new MessageResponse("Acceso denegado"));

        } catch (ResponseStatusException e) {
            logger.error("Error de estacionamiento: {}", e.getMessage(), e);
            return ResponseEntity.status(e.getStatusCode())
                    .body(new MessageResponse(e.getReason()));
        } catch (Exception e) {
            logger.error("Error desconocido al obtener vehículos: {}", e.getMessage(), e); // Cambiar mensaje
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error al procesar la solicitud"));
        }

    }

    private Integer extractUserFromToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new SecurityException("Token de autorización requerido");
        }

        String token = authHeader.substring(7);

        if (!jwtTokenUtil.validateToken(token)) {
            throw new SecurityException("Token inválido o expirado");
        }

        return jwtTokenUtil.getUserIdFromJWT(token);
    }
}