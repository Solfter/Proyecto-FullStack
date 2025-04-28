package cl.alcoholicos.gestorestacionamiento.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {
    private int rut;
    private String dv;
    private String primerNombre;
    private String segundoNombre;
    private String apPaterno;
    private String apMaterno;
    private String correo;
    private int nroCelular;
}