package cl.alcoholicos.gestorestacionamiento.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioBasicDTO {
    private int rut;
    private String dv;
    private String primerNombre;
    private String segundoNombre;
    private String apPaterno;
    private String apMaterno;
    private String correo;
    private int nroCelular;
}
