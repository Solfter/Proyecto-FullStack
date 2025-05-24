package cl.alcoholicos.gestorestacionamiento.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.config.JwtTokenUtil;
import cl.alcoholicos.gestorestacionamiento.dto.LoginRequest;
import cl.alcoholicos.gestorestacionamiento.dto.LoginResponse;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.service.impl.UsuarioService;

@Service
public class AuthService {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        // Validar usuario contra el servicio principal
        UsuarioResponseDTO usuario = usuarioService.validateUser(loginRequest);
        
        if (usuario != null) {
            // Generar token JWT usando jwtTokenUtil
            String jwt = jwtTokenUtil.generateToken(usuario);
            
            // Crear respuesta
            LoginResponse response = new LoginResponse();
            response.setToken(jwt);
            response.setId(usuario.getRut());
            response.setCorreo(usuario.getCorreo());
            response.setNombre(usuario.getPrimerNombre());
            
            return response;
        }
        
        throw new AuthenticationException("Credenciales inválidas");
    }
}