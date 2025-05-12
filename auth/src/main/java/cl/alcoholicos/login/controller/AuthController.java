package main.java.cl.alcoholicos.login.controller;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = authService.login(loginRequest);
            return ResponseEntity.ok(loginResponse);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Error: " + e.getMessage()));
        }
    }
    
    @GetMapping("/user")
    public ResponseEntity<?> getUserProfile(@RequestHeader("Authorization") String authHeader) {
        try {
            // Extraer token del header
            String token = authHeader.substring(7);
            
            if (jwtTokenProvider.validateToken(token)) {
                Long userId = jwtTokenProvider.getUserIdFromJWT(token);
                UserInfoDTO userInfo = userServiceClient.getUserDetails(userId, token);
                return ResponseEntity.ok(userInfo);
            }
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Token inválido"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error al obtener perfil: " + e.getMessage()));
        }
    }
}