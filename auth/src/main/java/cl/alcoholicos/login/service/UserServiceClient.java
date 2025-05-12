package main.java.cl.alcoholicos.login.service;

@Service
public class UserServiceClient {

    @Value("${app.mainServiceUrl}")
    private String mainServiceUrl;
    
    private final RestTemplate restTemplate;
    
    public UserServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    
    public UserInfoDTO validateUser(LoginRequest loginRequest) {
        String url = mainServiceUrl + "/usuarios/validar";
        
        try {
            ResponseEntity<UserInfoDTO> response = restTemplate.postForEntity(
                url, 
                loginRequest, 
                UserInfoDTO.class);
            
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new AuthenticationException("Credenciales inválidas");
            }
            throw new ServiceCommunicationException("Error al comunicarse con el servicio principal", e);
        }
        
        return null;
    }
    
    public UserInfoDTO getUserDetails(Long userId, String token) {
        String url = mainServiceUrl + "/usuarios/" + userId;
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        try {
            ResponseEntity<UserInfoDTO> response = restTemplate.exchange(
                url, 
                HttpMethod.GET, 
                entity, 
                UserInfoDTO.class);
                
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new ServiceCommunicationException("Error al obtener datos del usuario", e);
        }
    }
}
