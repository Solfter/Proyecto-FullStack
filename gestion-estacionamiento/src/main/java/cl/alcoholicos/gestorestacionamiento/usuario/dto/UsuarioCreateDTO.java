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
    @NotNull(message = "El RUT no puede ser nulo")
    private int rut;
    
    @NotNull(message = "El DV no puede ser nulo")
    private String dv;
    
    @NotBlank(message = "El primer nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String primerNombre;

    @Size(max = 50)
    private String segundoNombre;
    
    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(min = 2, max= 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String apPaterno;
    
    @Size(max = 50)
    private String apMaterno;
    
    @Email
    @NotBlank(message = "El correo es obligatorio")
    private String correo;
    
    @NotNull(message = "El número de celular es obligatorio")
    private int nroCelular;
    
    @NotNull(message = "Debes ingresar una contraseña")
    @Size(min = 8, message = "La contraseña debe contener 8 caracteres")
    private String password;
}