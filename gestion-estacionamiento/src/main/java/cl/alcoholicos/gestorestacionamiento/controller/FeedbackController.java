package cl.alcoholicos.gestorestacionamiento.controller;

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

import cl.alcoholicos.gestorestacionamiento.dto.FeedbackCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.FeedbackResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.MessageResponse;
import cl.alcoholicos.gestorestacionamiento.entity.FeedbackEntity;
import cl.alcoholicos.gestorestacionamiento.exception.ResourceNotFoundException;
import cl.alcoholicos.gestorestacionamiento.exception.ServiceUnavailableException;
import cl.alcoholicos.gestorestacionamiento.service.impl.FeedbackService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import cl.alcoholicos.gestorestacionamiento.config.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/feedback")
@AllArgsConstructor
@Tag(name = "Feedback", description = "API para la gestión de feedback y comentarios de usuarios")
public class FeedbackController {

    private static final Logger logger = LoggerFactory.getLogger(ReservaController.class);
    private final FeedbackService feedbackService;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping
    @Operation(
        summary = "Obtener todos los feedback",
        description = "Retorna una lista con todos los feedback registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de feedback obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = FeedbackEntity.class)
            )
        ),
        @ApiResponse(
            responseCode = "204",
            description = "No hay feedback registrados",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<List<FeedbackEntity>> getAll() {
        List<FeedbackEntity> feedbacks = feedbackService.getAll();
        if (feedbacks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(feedbacks);
    }

    @PostMapping
    @Operation(
        summary = "Crear un nuevo feedback",
        description = "Registra un nuevo feedback en el sistema. Requiere autenticación JWT."
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Feedback creado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = FeedbackResponseDTO.class)
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
            responseCode = "401",
            description = "Token de autorización inválido o expirado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acceso denegado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
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
        ),
        @ApiResponse(
            responseCode = "503",
            description = "Servicio temporalmente no disponible",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class)
            )
        )
    })
    public ResponseEntity<?> insert(
            @Parameter(description = "Datos del feedback a crear", required = true)
            @RequestBody FeedbackCreateDTO createDTO,
            @Parameter(description = "Token de autorización JWT (Bearer token)", required = true, example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
            @RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse("Se requiere toekn de autorización"));
            }

            String token = authHeader.substring(7);
            logger.debug("Token recibido en /user: {}", token.length() > 20 ? token.substring(0, 20) + "..." : token);

            if (!jwtTokenUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse("Token inválido o expirado"));
            }

            Integer rutUsuario = jwtTokenUtil.getUserIdFromJWT(token);
            logger.debug("UserId extraído del token: {}", rutUsuario);

            FeedbackResponseDTO nuevoFeedback = feedbackService.insert(createDTO, rutUsuario);

            return ResponseEntity.ok(nuevoFeedback);

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

    @PutMapping("/{idFeedback}")
    @Operation(
        summary = "Actualizar feedback",
        description = "Actualiza los datos de un feedback existente identificado por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Feedback actualizado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = FeedbackEntity.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Feedback no encontrado",
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
    public ResponseEntity<FeedbackEntity> update(
        @Parameter(description = "ID del feedback a actualizar", required = true, example = "1")
        @PathVariable Integer idFeedback,
        @Parameter(description = "Datos del feedback a actualizar", required = true)
        @RequestBody FeedbackEntity feedback) {
        FeedbackEntity feedbackExistente = feedbackService.getById(idFeedback);
        if (feedbackExistente == null) {
            return ResponseEntity.notFound().build();
        }
        FeedbackEntity feedbackActuailizado = feedbackService.update(idFeedback, feedback);
        return ResponseEntity.ok(feedbackActuailizado);
    }

    @DeleteMapping("/{idFeedback}")
    @Operation(
        summary = "Eliminar feedback",
        description = "Elimina un feedback del sistema usando su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Feedback eliminado exitosamente",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Feedback no encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = @Content
        )
    })
    public ResponseEntity<FeedbackEntity> delete(
        @Parameter(description = "ID del feedback a eliminar", required = true, example = "1")
        @PathVariable Integer idFeedback) {
        FeedbackEntity feedback = feedbackService.getById(idFeedback);
        if (feedback == null) {
            return ResponseEntity.notFound().build();
        }

        boolean feedbackBorrado = feedbackService.delete(idFeedback);
        if (feedbackBorrado) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

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
