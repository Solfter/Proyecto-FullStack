package cl.alcoholicos.gestorestacionamiento.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioLoginDTO {
    @NotNull
    private int rut;
    
    @NotNull
    private char dv;
    
    @NotBlank
    private String password;
}
