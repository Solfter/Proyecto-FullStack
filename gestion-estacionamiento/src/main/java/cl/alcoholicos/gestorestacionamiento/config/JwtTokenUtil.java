package cl.alcoholicos.gestorestacionamiento.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    // Usa la anotación @Value para inyectar el valor de la propiedad app.jwtSecret desde el archivo de configuración.
    @Value("${app.jwtSecret:defaultSecretKey12345678901234567890}")
    private String secret;

    // Inyecta el tiempo de expiración del token desde la configuración.
    @Value("${app.jwtSecret.expiration:86400}")
    private Long expiration; // 24 horas por defecto

    // Convierte la cadena de texto secret en una clave segura utilizando la clase Keys de la librería. Esta clave se utiliza para firmar los tokens JWT.
    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes); 
    }

    // Método público para generar un token JWT para un usuario. Crea un mapa vacío de claims (información del token) y llama al método createToken.
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    // Método privado que crea el token JWT. Establece la fecha actual y calcula la fecha de expiración sumando el tiempo de expiración en milisegundos.
    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration * 1000);

        return Jwts.builder()
                .setClaims(claims) // Establece cualquier información adicional
                .setSubject(subject) // Establece el sujeto del token (el nombre de usuario)
                .setIssuedAt(now) // Establece cuándo se emitió el token
                .setExpiration(expiryDate) // Establece cuándo expira el token
                .signWith(getSigningKey(), SignatureAlgorithm.HS512) // Firma el token con la clave secreta usando el algoritmo HS512
                .compact(); // Convierte el token en una cadena compacta
    }

    // Valida un token verificando que el nombre de usuario extraído del token coincida con el nombre de usuario proporcionado y que el token no haya expirado.
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Extrae el nombre de usuario (sujeto) del token JWT.
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extrae la fecha de expiración del token JWT.
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Método genérico para extraer cualquier información del token. Recibe una función que determina qué campo extraer de los claims.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extrae todos los claims del token JWT. Utiliza la misma clave secreta que se usó para firmar el token para verificar su autenticidad.
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Verifica si el token ha expirado comparando la fecha de expiración con la fecha actual.
    private Boolean isTokenExpired(String token) {
        final Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }
}