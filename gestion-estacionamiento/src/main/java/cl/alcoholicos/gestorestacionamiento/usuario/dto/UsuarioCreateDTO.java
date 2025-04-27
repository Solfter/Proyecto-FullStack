package cl.alcoholicos.gestorestacionamiento.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioCreateDTO {
    @NotNull
    private int rut;
    
    @NotNull
    private char dv;
    
    @NotBlank
    private String pNombre;
    
    private String sNombre;
    
    @NotBlank
    private String apPaterno;
    
    private String apMaterno;
    
    @Email
    @NotBlank
    private String correo;
    
    private int nroCelular;
    
    @NotBlank
    @Size(min = 8)
    private String password;
}