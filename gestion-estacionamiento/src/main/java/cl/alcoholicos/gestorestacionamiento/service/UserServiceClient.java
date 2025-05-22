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

/*
1. Se comunica con otro microservicio (el servicio de usuarios) a través de peticiones HTTP REST
2. Pasa el token JWT para autenticación entre servicios
3. Maneja diferentes tipos de errores que pueden ocurrir en la comunicación entre servicios
4. Transforma las excepciones HTTP en excepciones específicas de la aplicación
5. Registra información detallada sobre las operaciones y los errores
 */
@Service
public class UserServiceClient {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceClient.class); // Crea un logger para registrar eventos y errores en esta clase.
    
    private final RestTemplate restTemplate; // Declara una variable final para el RestTemplate, que es la clase principal de Spring para realizar peticiones HTTP.
    
    // Utiliza la anotación @Value para inyectar el valor de la propiedad user-service.url desde el archivo de configuración. Esta URL apunta al servicio de usuarios.
    @Value("${user-service.url}")
    private String userServiceUrl;
    
    // Constructor que inicializa el RestTemplate que se utilizará para realizar las peticiones HTTP.
    public UserServiceClient() {
        this.restTemplate = new RestTemplate();
    }
    
    public UsuarioResponseDTO getUserDetails(Long userId, String token) {
        try {
            // Crea un objeto HttpHeaders y establece el encabezado de autorización con el token JWT proporcionado. Luego crea una entidad HTTP con estos encabezados.
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            // Construye la URL completa para la petición y registra un mensaje informativo en el log.
            String url = userServiceUrl + "/usuarios/" + userId;
            logger.info("Solicitando detalles de usuario desde: {}", url);
            
            // Realiza una petición HTTP GET al servicio de usuarios, pasando la entidad con los encabezados de autorización. Espera recibir un objeto UsuarioResponseDTO como respuesta.
            ResponseEntity<UsuarioResponseDTO> response = restTemplate.exchange(
                url, 
                HttpMethod.GET, 
                entity, 
                UsuarioResponseDTO.class
            );
            
            // Verifica que el cuerpo de la respuesta no sea nulo. Si es nulo, lanza una excepción indicando que el usuario no se encontró.
            if (response.getBody() == null) {
                throw new ResourceNotFoundException("No se encontró información para el usuario con ID: " + userId);
            }
            
            return response.getBody(); // Devuelve el cuerpo de la respuesta, que contiene los detalles del usuario.
            
        } catch (HttpClientErrorException.NotFound e) { // Captura la excepción específica para el caso de que el recurso no se encuentre (404), registra el error y lanza una excepción más específica de la aplicación.
            logger.error("Usuario no encontrado: {}", e.getMessage());
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + userId);
        } catch (HttpClientErrorException.Unauthorized | HttpClientErrorException.Forbidden e) { // Captura las excepciones relacionadas con problemas de autorización (401 o 403), registra el error y lanza una excepción de seguridad.
            logger.error("Error de autorización al acceder al servicio de usuarios: {}", e.getMessage());
            throw new SecurityException("No autorizado para acceder a la información del usuario");
        } catch (Exception e) { // Captura cualquier otra excepción, registra el error y lanza una excepción que indica que el servicio no está disponible.
            logger.error("Error al comunicarse con el servicio de usuarios: {}", e.getMessage());
            throw new ServiceUnavailableException("Error al comunicarse con el servicio de usuarios: " + e.getMessage());
        }
    }
}