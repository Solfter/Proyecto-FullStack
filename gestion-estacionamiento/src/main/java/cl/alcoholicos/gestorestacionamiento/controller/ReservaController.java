package cl.alcoholicos.gestorestacionamiento.controller;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;
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
import cl.alcoholicos.gestorestacionamiento.dto.ReservaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.exception.EstacionamientoNotFoundException;
import cl.alcoholicos.gestorestacionamiento.exception.ResourceNotFoundException;
import cl.alcoholicos.gestorestacionamiento.exception.ServiceUnavailableException;
import cl.alcoholicos.gestorestacionamiento.service.impl.ReservaService;

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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/reserva")
@Tag(name = "Reservas", description = "API para la gestión de reservas de estacionamiento")
public class ReservaController {

    private static final Logger logger = LoggerFactory.getLogger(ReservaController.class);
    private final ReservaService reservaService;
    private final JwtTokenUtil jwtTokenUtil;

    @Operation(summary = "Obtener todas las reservas", description = "Retrieve todas las reservas existentes en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de reservas obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReservaResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "No se encontraron reservas", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> getAll() {
        List<ReservaResponseDTO> reservas = reservaService.getAll();
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

    @Operation(summary = "Obtener reserva por ID", description = "Obtiene una reserva específica mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva encontrada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReservaResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Reserva no encontrada", content = @Content),
            @ApiResponse(responseCode = "400", description = "ID de reserva inválido", content = @Content)
    })
    @GetMapping("/id/{idReserva}")
    public ResponseEntity<ReservaResponseDTO> getById(
            @Parameter(description = "ID único de la reserva", required = true, example = "1") @PathVariable Integer idReserva) {
        ReservaResponseDTO reserva = reservaService.getByReservaId(idReserva);
        if (reserva == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reserva);
    }

    @GetMapping("horaFin/{nroEstacionamiento}")
    @Operation(
        summary = "Obtener hora de fin de reserva por estacionamiento",
        description = "Retorna la hora en que se desocupa un estacionamiento específico"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Hora de fin obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Estacionamiento no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<?> obtenerHoraFin(
        @Parameter(description = "Número del estacionamiento", required = true, example = "1")
        @PathVariable Integer nroEstacionamiento) {
        LocalTime horaFin = reservaService.buscarHoraFinPorEstacionamiento(nroEstacionamiento);
        return new ResponseEntity<>("El estacionamiento nro \"" + nroEstacionamiento + "\" se desocupa a las " + horaFin, HttpStatus.OK);
    }

    @Operation(summary = "Crear nueva reserva", description = "Crea una nueva reserva de estacionamiento. Requiere autenticación JWT.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva creada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReservaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o incompletos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "401", description = "Token de autorización inválido o expirado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "403", description = "Acceso denegado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "503", description = "Servicio temporalmente no disponible", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class)))
    })
    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<?> insert(
            @Parameter(description = "Datos para crear la reserva", required = true) @RequestBody ReservaCreateDTO createDTO,

            @Parameter(description = "Token de autorización JWT (Bearer token)", required = true, example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...") @RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse("Se requiere token de autorización"));
            }

            String token = authHeader.substring(7);
            logger.debug("Token recibido en /user: {}", token.length() > 20 ? token.substring(0, 20) + "..." : token);

            if (!jwtTokenUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse("Token inválido o expirado"));
            }

            // Extrae el ID del usuario del token JWT
            Integer rutUsuario = jwtTokenUtil.getUserIdFromJWT(token);
            logger.debug("UserId extraído del token: {}", rutUsuario);

            ReservaResponseDTO nuevaReserva = reservaService.insert(createDTO, rutUsuario);

            return ResponseEntity.ok(nuevaReserva);

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
            logger.error("Error desconocido al obtener perfil: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error al procesar la solicitud"));
        }
    }

    @GetMapping("/historial")
    @Operation(
        summary = "Obtener historial de reservas del usuario",
        description = "Retorna todas las reservas del usuario autenticado. Requiere autenticación JWT."
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Historial de reservas obtenido exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ReservaResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay reservas para el usuario",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Token de autorización inválido o expirado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class)
            )
        )
    })
    public ResponseEntity<?> obtenerReservaPorUsuario(
        @Parameter(description = "Token de autorización JWT (Bearer token)", required = true, example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        @RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse("Se requiere token de autorización"));
            }

            String token = authHeader.substring(7);
            logger.debug("Token recibido en /user: {}", token.length() > 20 ? token.substring(0, 20) + "..." : token);

            if (!jwtTokenUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse("Token inválido o expirado"));
            }

            // Extrae el ID del usuario del token JWT
            Integer rutUsuario = jwtTokenUtil.getUserIdFromJWT(token);
            logger.debug("UserId extraído del token: {}", rutUsuario);

            List<ReservaResponseDTO> reservas = reservaService.getByUserId(rutUsuario);
            if (reservas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(reservas);

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
            logger.error("Error desconocido al obtener perfil: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error al procesar la solicitud"));
        }
    }

    @PutMapping("/{idReserva}")
    @Operation(
        summary = "Actualizar reserva",
        description = "Actualiza una reserva existente. Requiere autenticación JWT y permisos para modificar la reserva."
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Reserva actualizada exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ReservaResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos o argumentos incorrectos",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Token de autorización inválido o expirado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "403",
            description = "No tienes permisos para modificar esta reserva",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Reserva no encontrada",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Conflicto con el estado actual de la reserva",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class)
            )
        )
    })
    public ResponseEntity<?> actualizarReserva(
            @Parameter(description = "Datos de la reserva a actualizar", required = true)
            @RequestBody @Valid ReservaUpdateDTO updateDTO,
            @Parameter(description = "Token de autorización JWT (Bearer token)", required = true, example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
            @RequestHeader("Authorization") String authHeader,
            @Parameter(description = "ID de la reserva a actualizar", required = true, example = "1")
            @PathVariable Integer idReserva) {

        try {
            // Extraer usuario del token
            Integer rutUsuario = extractUserFromToken(authHeader);

            ReservaResponseDTO reservaActualizada = reservaService.update(idReserva, updateDTO, rutUsuario);

            return ResponseEntity.ok(reservaActualizada);

        } catch (SecurityException e) {
            logger.error("Error de autorización: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new MessageResponse("No tienes permisos para modificar esta reserva"));

        } catch (IllegalArgumentException e) {
            logger.error("Argumentos inválidos: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(new MessageResponse(e.getMessage()));

        } catch (IllegalStateException e) {
            logger.error("Estado inválido: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new MessageResponse(e.getMessage()));

        } catch (EntityNotFoundException e) {
            logger.error("Reserva no encontrada: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse(e.getMessage()));

        } catch (DataIntegrityViolationException e) {
            logger.error("Error de integridad de datos: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Datos incompletos"));

        } catch (Exception e) {
            logger.error("Error inesperado al actualizar reserva: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error interno del servidor"));
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

    @DeleteMapping("/{idReserva}")
    @Operation(
        summary = "Eliminar reserva",
        description = "Elimina una reserva del sistema usando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Reserva eliminada exitosamente",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Reserva no encontrada",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "No se puede eliminar la reserva porque está siendo procesada",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID de la reserva a eliminar", required = true, example = "1")
        @PathVariable Integer idReserva) {
        boolean deleted = reservaService.delete(idReserva);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}