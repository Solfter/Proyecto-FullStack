package cl.alcoholicos.gestorestacionamiento.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter { // OncePerRequestFilter garantiza que el filtro se ejecute una vez por cada solicitud HTTP.

    @Autowired
    private UserDetailsService userDetailsService; // Carga información del usuario desde la base de datos.

    @Autowired
    private JwtTokenUtil jwtTokenUtil; // Proporciona métodos para manipular y validar tokens JWT.

    @Override
    // Sobrescribe el método doFilterInternal que se ejecuta para cada solicitud. Recibe la solicitud, respuesta y la cadena de filtros.
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException { 

        final String authorizationHeader = request.getHeader("Authorization"); // Obtiene el valor del encabezado "Authorization" de la solicitud HTTP.

        // Declaración de variables para almacenar el nombre de usuario y el token JWT.
        String username = null;
        String jwt = null;

        // Extrae el token del encabezado Authorization (formato: "Bearer token")
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Extrae el token JWT quitando los primeros 7 caracteres ("Bearer "). 
            try {
                username = jwtTokenUtil.extractUsername(jwt); // Intenta extraer el nombre de usuario del token JWT.
            } catch (Exception e) {
                logger.error("Error al validar el token JWT", e);
            }
        }

        // Verifica si se obtuvo un nombre de usuario y si no hay una autenticación establecida en el contexto de seguridad.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            // Carga los detalles del usuario desde la base de datos usando el nombre de usuario extraído del token.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); 

            // Verifica que el token sea válido y corresponda al usuario
            if (jwtTokenUtil.validateToken(jwt, userDetails)) {
                // Crea un objeto de autenticación con los detalles del usuario y sus autoridades (roles y permisos).
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                // Agrega detalles adicionales al objeto de autenticación, como la dirección IP y la sesión.
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Establece la autenticación en el contexto de seguridad de Spring, lo que permite que el usuario sea reconocido como autenticado en toda la aplicación.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response); // Continúa con la cadena de filtros, permitiendo que la solicitud siga su procesamiento normal.
    }
}