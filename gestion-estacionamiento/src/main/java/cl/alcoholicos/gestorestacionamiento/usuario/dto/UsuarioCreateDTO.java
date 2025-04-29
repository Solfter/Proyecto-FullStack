package cl.alcoholicos.gestorestacionamiento.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Min(1)
    private int rut;
    
    @NotNull(message = "El DV no puede ser nulo")
    @Size(max = 1, message = "El DV solo debe ser 1 caracter")
    @Pattern(regexp = "[0-9Kk]", message = "DV inválido")
    private String dv;
    
    @NotBlank(message = "El primer nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String primerNombre;

    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String segundoNombre;
    
    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(min = 2, max= 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String apPaterno;
    
    @Size(max = 50)
    private String apMaterno;
    
    @Email
    @NotBlank(message = "El correo es obligatorio")
    @Size(max = 100, message = "Correo demasiado largo")
    private String correo;
    
    @NotNull(message = "El número de celular es obligatorio")
    @Min(value = 100000000, message = "El número de celular debe tener 9 dígitos")
    @Max(value = 999999999, message = "El número de celular debe tener 9 dígitos")
    private int nroCelular;
    
    @NotNull(message = "Debes ingresar una contraseña")
    @Size(min = 8, message = "La contraseña debe contener 8 caracteres")
    private String password;
}