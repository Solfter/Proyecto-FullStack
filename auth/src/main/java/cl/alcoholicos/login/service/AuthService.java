package main.java.cl.alcoholicos.login.service;

@Service
public class AuthService {

    @Autowired
    private UserServiceClient userServiceClient;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    public LoginResponse login(LoginRequest loginRequest) {
        // Validar usuario contra el servicio principal
        UserInfoDTO userInfo = userServiceClient.validateUser(loginRequest);
        
        if (userInfo != null) {
            // Generar token JWT
            String jwt = jwtTokenProvider.generateToken(userInfo);
            
            // Crear respuesta
            LoginResponse response = new LoginResponse();
            response.setToken(jwt);
            response.setId(userInfo.getId());
            response.setEmail(userInfo.getEmail());
            response.setNombre(userInfo.getNombre());
            response.setRoles(userInfo.getRoles());
            
            return response;
        }
        
        throw new AuthenticationException("Credenciales inválidas");
    }
}