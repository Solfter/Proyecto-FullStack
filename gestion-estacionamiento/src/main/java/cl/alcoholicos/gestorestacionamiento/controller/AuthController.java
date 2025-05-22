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

import cl.alcoholicos.gestorestacionamiento.security.JwtTokenProvider;
import cl.alcoholicos.gestorestacionamiento.dto.LoginRequest;
import cl.alcoholicos.gestorestacionamiento.dto.LoginResponse;
import cl.alcoholicos.gestorestacionamiento.dto.MessageResponse;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.exception.ResourceNotFoundException;
import cl.alcoholicos.gestorestacionamiento.exception.ServiceUnavailableException;
import cl.alcoholicos.gestorestacionamiento.service.AuthService;
import cl.alcoholicos.gestorestacionamiento.service.UserServiceClient;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    // Crea un logger para registrar eventos y errores en el controlador.
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    // Inyecta el servicio de autenticación que se encargará de la lógica de login.
    @Autowired
    private AuthService authService;
    
    // Inyecta el cliente de servicio de usuario, probablemente un cliente para comunicarse con otro microservicio que gestiona los usuarios.
    @Autowired
    private UserServiceClient userServiceClient;
    
    // Inyecta el proveedor de tokens JWT que se utiliza para generar, validar y extraer información de los tokens.
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        // Intenta autenticar al usuario llamando al método login del servicio de autenticación y devuelve una respuesta exitosa con el objeto LoginResponse.
        try {
            LoginResponse loginResponse = authService.login(loginRequest);
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) { // Captura cualquier excepción durante el proceso de autenticación, la registra y devuelve una respuesta 401 (UNAUTHORIZED) con un mensaje de error.
            logger.error("Error en autenticación: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new MessageResponse("Error de autenticación: " + e.getMessage()));
        }
    }
    
    @GetMapping("/user")
    public ResponseEntity<?> getUserProfile(@RequestHeader("Authorization") String authHeader) {
        try {
            // Verifica que el encabezado de autorización existe y comienza con "Bearer ".
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse("Se requiere token de autorización"));
            }
            
            // Extrae el token JWT del encabezado quitando los primeros 7 caracteres ("Bearer ").
            String token = authHeader.substring(7);
            
            // Valida el token JWT utilizando el proveedor de tokens.
            if (!jwtTokenProvider.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse("Token inválido o expirado"));
            }
            
            // Extrae el ID del usuario del token JWT.
            Long userId = jwtTokenProvider.getUserIdFromJWT(token);
            
            // Obtiene los detalles del usuario utilizando el cliente de servicio de usuario, pasando el ID del usuario y el token para autorización.
            UsuarioResponseDTO userInfo = userServiceClient.getUserDetails(userId, token);
            
            return ResponseEntity.ok(userInfo);
            
        } catch (ExpiredJwtException e) { // Captura la excepción específica para tokens JWT expirados y devuelve un mensaje apropiado.
            logger.error("JWT expirado: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Token expirado, por favor inicie sesión nuevamente"));
                    
        } catch (UnsupportedJwtException e) { // Captura la excepción para tokens JWT con formato no soportado.
            logger.error("JWT no soportado: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Formato de token no soportado"));
                    
        } catch (MalformedJwtException e) { // Captura la excepción para tokens JWT malformados.
            logger.error("JWT malformado: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Token inválido"));
                    
        } catch (SignatureException e) { // Captura la excepción para tokens JWT con firma inválida.
            logger.error("Error en firma del JWT: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Token con firma inválida"));
                    
        } catch (ResourceNotFoundException e) { // Captura la excepción cuando el usuario no se encuentra.
            logger.error("Usuario no encontrado: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse(e.getMessage()));
                    
        } catch (ServiceUnavailableException e) { // Captura la excepción cuando el servicio de usuario no está disponible.
            logger.error("Servicio no disponible: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new MessageResponse("Servicio temporalmente no disponible"));
                
        } catch (SecurityException e) { // Captura excepciones de seguridad y devuelve un mensaje de acceso denegado.
            logger.error("Error de seguridad: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new MessageResponse("Acceso denegado"));
                    
        } catch (Exception e) { // Captura cualquier otra excepción no manejada específicamente y devuelve un error interno del servidor.
            logger.error("Error desconocido al obtener perfil: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error al procesar la solicitud"));
        }
    }
}