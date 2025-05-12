package cl.alcoholicos.gestorestacionamiento.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import cl.alcoholicos.gestorestacionamiento.dto.UsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.exception.ResourceNotFoundException;
import cl.alcoholicos.gestorestacionamiento.exception.ServiceUnavailableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceClient {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceClient.class);
    
    private final RestTemplate restTemplate;
    
    @Value("${user-service.url}")
    private String userServiceUrl;
    
    public UserServiceClient() {
        this.restTemplate = new RestTemplate();
    }
    
    /**
     * Recupera los detalles de un usuario por su ID desde el servicio de usuarios
     * 
     * @param userId ID del usuario a recuperar
     * @param token Token JWT para autorización
     * @return Objeto UsuarioResponseDTO con los detalles del usuario
     * @throws ResourceNotFoundException Si el usuario no existe
     * @throws ServiceUnavailableException Si hay un problema con el servicio de usuarios
     */
    public UsuarioResponseDTO getUserDetails(Long userId, String token) {
        try {
            // Preparar headers con el token de autorización
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            // Construir la URL para el endpoint específico
            String url = userServiceUrl + "/usuarios/" + userId;
            logger.info("Solicitando detalles de usuario desde: {}", url);
            
            // Realizar la petición HTTP
            ResponseEntity<UsuarioResponseDTO> response = restTemplate.exchange(
                url, 
                HttpMethod.GET, 
                entity, 
                UsuarioResponseDTO.class
            );
            
            // Verificar y devolver la respuesta
            if (response.getBody() == null) {
                throw new ResourceNotFoundException("No se encontró información para el usuario con ID: " + userId);
            }
            
            return response.getBody();
            
        } catch (HttpClientErrorException.NotFound e) {
            logger.error("Usuario no encontrado: {}", e.getMessage());
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + userId);
        } catch (HttpClientErrorException.Unauthorized | HttpClientErrorException.Forbidden e) {
            logger.error("Error de autorización al acceder al servicio de usuarios: {}", e.getMessage());
            throw new SecurityException("No autorizado para acceder a la información del usuario");
        } catch (Exception e) {
            logger.error("Error al comunicarse con el servicio de usuarios: {}", e.getMessage());
            throw new ServiceUnavailableException("Error al comunicarse con el servicio de usuarios: " + e.getMessage());
        }
    }
}