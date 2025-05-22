package cl.alcoholicos.gestorestacionamiento.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/* 
Esta clase es crucial para la configuración de seguridad de la aplicación ya que:

Define cómo se cifran y verifican las contraseñas
Configura qué rutas son públicas y cuáles requieren autenticación
Establece una política de sesión sin estado (stateless) para usar con JWT
Integra el filtro JWT personalizado en la cadena de filtros de seguridad de Spring
*/

@Configuration
@EnableWebSecurity // Habilita la seguridad web de Spring Security para la aplicación.
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter; // Inyecta el filtro JWT que se encargará de procesar los tokens JWT en cada solicitud.

    /* Define un bean PasswordEncoder que utiliza el algoritmo BCrypt para cifrar y verificar contraseñas. 
    Este encoder se utiliza automáticamente por Spring Security para autenticar usuarios. */ 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /* Define un bean AuthenticationManager que se obtiene de la configuración de autenticación proporcionada por Spring. 
    Este manager es responsable de procesar intentos de autenticación. */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    
    /* Define el bean SecurityFilterChain que configura las reglas de seguridad HTTP. 
    Este método recibe un objeto HttpSecurity que se utiliza para configurar la seguridad. */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Desactiva la protección CSRF (Cross-Site Request Forgery). Esto es común en APIs REST que utilizan JWT porque los tokens JWT ya proporcionan protección contra ataques CSRF.
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(authz -> authz
                // Configura las reglas de autorización para las solicitudes HTTP: Las rutas descritas son publicas y no requieren autenticación
                .requestMatchers("/auth/**", "/v3/api-docs/**", "/swagger-ui/**", "usuarios/**", "/**").permitAll()
                // Cualquier otra solicitud que no coincida con las rutas públicas requiere que el usuario esté autenticado.
                .anyRequest().authenticated()
            )
            /* Configura la política de creación de sesiones como STATELESS (sin estado). 
            Esto significa que el servidor no mantendrá información de sesión para los usuarios, lo cual es apropiado para APIs REST que utilizan JWT. */
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
        /* Añade el filtro JWT personalizado antes del filtro de autenticación estándar de Spring Security. 
        Esto asegura que el token JWT se procese antes de que Spring intente autenticar al usuario mediante nombre de usuario y contraseña. */
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build(); // Construye y devuelve la cadena de filtros de seguridad configurada.
    }
}