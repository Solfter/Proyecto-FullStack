package cl.alcoholicos.gestorestacionamiento.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Clase para la respuesta de autenticación
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
}
