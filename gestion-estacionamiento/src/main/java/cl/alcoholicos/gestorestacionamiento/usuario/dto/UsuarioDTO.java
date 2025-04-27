package cl.alcoholicos.gestorestacionamiento.usuario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private int rut;
    private Character dv;
    private String pNombre;    
    private String sNombre;    
    private String apPaterno;  
    private String apMaterno;  
    private String correo;
    private int nroCelular;
}
