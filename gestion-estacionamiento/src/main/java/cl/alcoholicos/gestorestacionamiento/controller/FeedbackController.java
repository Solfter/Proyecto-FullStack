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

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/feedback")
@AllArgsConstructor
public class FeedbackController {

    private static final Logger logger = LoggerFactory.getLogger(ReservaController.class);
    private final FeedbackService feedbackService;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping
    public ResponseEntity<List<FeedbackEntity>> getAll() {
        List<FeedbackEntity> feedbacks = feedbackService.getAll();
        if (feedbacks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(feedbacks);
    }

    @PostMapping
    public ResponseEntity<?> insert(
            @RequestBody FeedbackCreateDTO createDTO,
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
    public ResponseEntity<FeedbackEntity> update(@PathVariable Integer idFeedback,
            @RequestBody FeedbackEntity feedback) {
        FeedbackEntity feedbackExistente = feedbackService.getById(idFeedback);
        if (feedbackExistente == null) {
            return ResponseEntity.notFound().build();
        }
        FeedbackEntity feedbackActuailizado = feedbackService.update(idFeedback, feedback);
        return ResponseEntity.ok(feedbackActuailizado);
    }

    @DeleteMapping("/{idFeedback}")
    public ResponseEntity<FeedbackEntity> delete(@PathVariable Integer idFeedback) {
        FeedbackEntity feedback = feedbackService.getById(idFeedback);
        if (feedback == null) {
            return ResponseEntity.notFound().build();
        }

        FeedbackEntity feedbackBorrado = feedbackService.delete(idFeedback);
        if (feedbackBorrado == null) {
            return ResponseEntity.ok(feedback);
        }

        return ResponseEntity.badRequest().build();

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
