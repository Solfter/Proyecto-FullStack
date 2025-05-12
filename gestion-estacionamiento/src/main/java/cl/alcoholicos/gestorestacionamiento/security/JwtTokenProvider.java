package cl.alcoholicos.gestorestacionamiento.security;

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

@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    // Clave segura generada para HS512
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    
    @Value("${jwt.expiration:86400000}")
    private long jwtExpiration; // 24 horas por defecto

    // Método para generar token con UsuarioResponseDTO
    public String generateToken(UsuarioResponseDTO usuario) {
        Map<String, Object> claims = new HashMap<>();
        // Solo incluir información esencial en el token
        claims.put("id", usuario.getRut());
        claims.put("roles", usuario.getRoles()); // Solo los nombres de los roles, no los objetos completos
        
        return createToken(claims, usuario.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    // Validar token con UserDetails
    public Boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (Exception e) {
            logger.error("Error validando token para usuario: " + e.getMessage());
            return false;
        }
    }
    
    // Validar token sin necesidad de UserDetails (sólo verifica la firma y la expiración)
    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
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
    public Long getUserIdFromJWT(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return claims.get("id", Long.class);
        } catch (ExpiredJwtException e) {
            // Aún puedes extraer información de un token expirado
            return e.getClaims().get("id", Long.class);
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // Para casos donde solo necesitamos extraer información de un token expirado
            return e.getClaims();
        }
    }

    private Boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }
}