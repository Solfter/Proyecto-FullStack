package cl.alcoholicos.gestorestacionamiento.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import cl.alcoholicos.gestorestacionamiento.dto.UsuarioResponseDTO;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


/*
Propósito: 
Utilidad para crear, validar y extraer información de tokens JWT.

Responsabilidades:
Generar tokens JWT: Crear nuevos tokens al hacer login
Validar tokens: Verificar firma, expiración y formato
Extraer información: Obtener username, fecha de expiración, etc.
Manejar la clave secreta: Para firmar y verificar tokens

Métodos típicos:
- generateToken(String username) → String
- validateToken(String token, String username) → boolean
- extractUsername(String token) → String
- isTokenExpired(String token) → boolean
- extractExpiration(String token) → Date
 */
@Component
public class JwtTokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    

    @Value("${app.jwtSecret}")
    private String secret;

    @Value("${app.jwtSecret.expiration:86400}")
    private Long expiration; // en segundos

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes); 
    }

    // Método original para generar token con UserDetails
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    // Método para generar token con UsuarioResponseDTO
    public String generateToken(UsuarioResponseDTO usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", usuario.getRut());
        claims.put("roles", usuario.getRoles());
        
        // Usar correo como subject para consistencia con UserDetailsService
        return createToken(claims, usuario.getCorreo());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (Exception e) {
            logger.error("Error validando token para usuario: {}", e.getMessage());
            return false;
        }
    }

    // Validar token sin UserDetails (solo verificar firma y expiración)
    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (SignatureException e) {
            logger.error("Firma JWT inválida: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Token JWT inválido: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Token JWT expirado: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Token JWT no soportado: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string está vacío: {}", e.getMessage());
        }
        return false;
    }

    // Obtener ID de usuario desde el token
    public Integer getUserIdFromJWT(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return claims.get("id", Integer.class);
        } catch (ExpiredJwtException e) {
            return e.getClaims().get("id", Integer.class);
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // Para casos donde necesitamos extraer información de token expirado
            return e.getClaims();
        }
    }

    private Boolean isTokenExpired(String token) {
        try {
            final Date expiration = extractExpiration(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }
}