package cl.alcoholicos.gestorestacionamiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoResponseDTO {

    private String patente;
    
    private String color;

    private int anio;

    private ModeloResponseDTO modelo;

    private UsuarioBasicDTO usuario;
    
}
