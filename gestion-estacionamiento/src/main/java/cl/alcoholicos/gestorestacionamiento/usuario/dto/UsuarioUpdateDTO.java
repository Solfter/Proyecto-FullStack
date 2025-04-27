package cl.alcoholicos.gestorestacionamiento.usuario.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioUpdateDTO {
    private String pNombre;
    private String sNombre;
    private String apPaterno;
    private String apMaterno;
    
    @Email
    private String correo;
    
    private Integer nroCelular;
}

