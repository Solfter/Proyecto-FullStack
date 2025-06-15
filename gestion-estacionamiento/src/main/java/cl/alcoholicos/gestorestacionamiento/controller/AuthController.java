package cl.alcoholicos.gestorestacionamiento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.alcoholicos.gestorestacionamiento.config.JwtTokenUtil;
import cl.alcoholicos.gestorestacionamiento.dto.LoginRequest;
import cl.alcoholicos.gestorestacionamiento.dto.LoginResponse;
import cl.alcoholicos.gestorestacionamiento.dto.MessageResponse;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.exception.ResourceNotFoundException;
import cl.alcoholicos.gestorestacionamiento.exception.ServiceUnavailableException;
import cl.alcoholicos.gestorestacionamiento.service.impl.AuthService;
import cl.alcoholicos.gestorestacionamiento.service.impl.UserServiceClient;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Endpoints para autenticación y manejo de sesiones de usuario")
public class AuthController {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;
    
    @Autowired
    private UserServiceClient userServiceClient;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    @Operation(
        summary = "Iniciar sesión",
        description = "Autentica un usuario con email y contraseña, retornando un token JWT válido para acceder a los endpoints protegidos"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Autenticación exitosa",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = LoginResponse.class),
                examples = @ExampleObject(  
                    name = "Login exitoso",
                    value = """
                    {
                        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                        "type": "Bearer",
                        "userId": 123,
                        "email": "usuario@example.com",
                        "roles": ["USER"]
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "401", 
            description = "Credenciales inválidas",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class),
                examples = @ExampleObject(
                    name = "Error de autenticación",
                    value = """
                    {
                        "message": "Error de autenticación: Credenciales inválidas"
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Datos de entrada inválidos",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class)
            )
        )
    })
    public ResponseEntity<?> authenticateUser(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Credenciales de acceso del usuario",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = LoginRequest.class),
                examples = @ExampleObject(
                    name = "Ejemplo de login",
                    value = """
                    {
                        "email": "usuario@example.com",
                        "password": "miPassword123"
                    }
                    """
                )
            )
        )
        @Valid @RequestBody LoginRequest loginRequest
    ) throws Exception {
        try {
            LoginResponse loginResponse = authService.login(loginRequest);
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            logger.error("Error en autenticación: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new MessageResponse("Error de autenticación: " + e.getMessage()));
        }
    }
    
    @GetMapping("/user")
    @Operation(
        summary = "Obtener perfil del usuario autenticado",
        description = "Retorna la información del perfil del usuario basado en el token JWT proporcionado en el header Authorization"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Perfil del usuario obtenido exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UsuarioResponseDTO.class),
                examples = @ExampleObject(
                    name = "Perfil de usuario",
                    value = """
                    {
                        "id": 123,
                        "email": "usuario@example.com",
                        "nombre": "Juan",
                        "apellido": "Pérez",
                        "telefono": "+56987654321",
                        "roles": ["USER"],
                        "fechaCreacion": "2025-01-15T10:30:00"
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "401", 
            description = "Token inválido, expirado o no proporcionado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class),
                examples = {
                    @ExampleObject(
                        name = "Token requerido",
                        value = """
                        {
                            "message": "Se requiere token de autorización"
                        }
                        """
                    ),
                    @ExampleObject(
                        name = "Token expirado",
                        value = """
                        {
                            "message": "Token expirado, por favor inicie sesión nuevamente"
                        }
                        """
                    ),
                    @ExampleObject(
                        name = "Token inválido",
                        value = """
                        {
                            "message": "Token inválido"
                        }
                        """
                    )
                }
            )
        ),
        @ApiResponse(
            responseCode = "403", 
            description = "Acceso denegado por motivos de seguridad",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class),
                examples = @ExampleObject(
                    name = "Acceso denegado",
                    value = """
                    {
                        "message": "Acceso denegado"
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Usuario no encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class),
                examples = @ExampleObject(
                    name = "Usuario no encontrado",
                    value = """
                    {
                        "message": "Usuario no encontrado"
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "503", 
            description = "Servicio de usuarios temporalmente no disponible",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class),
                examples = @ExampleObject(
                    name = "Servicio no disponible",
                    value = """
                    {
                        "message": "Servicio temporalmente no disponible"
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Error interno del servidor",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MessageResponse.class),
                examples = @ExampleObject(
                    name = "Error interno",
                    value = """
                    {
                        "message": "Error al procesar la solicitud"
                    }
                    """
                )
            )
        )
    })
    public ResponseEntity<?> getUserProfile(
        @Parameter(
            name = "Authorization",
            description = "Token JWT en formato Bearer. Formato: 'Bearer {token}'",
            required = true,
            example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
            schema = @Schema(type = "string", pattern = "^Bearer .+")
        )
        @RequestHeader("Authorization") String authHeader
    ) {
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
            Integer userId = jwtTokenUtil.getUserIdFromJWT(token);
            logger.debug("UserId extraído del token: {}", userId);
            
            // Obtiene los detalles del usuario
            UsuarioResponseDTO userInfo = userServiceClient.getUserDetails(userId, token);
            
            return ResponseEntity.ok(userInfo);
            
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
                    
        } catch (Exception e) {
            logger.error("Error desconocido al obtener perfil: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error al procesar la solicitud"));
        }
    }
}