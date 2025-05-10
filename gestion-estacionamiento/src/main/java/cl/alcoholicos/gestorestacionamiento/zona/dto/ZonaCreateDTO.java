package cl.alcoholicos.gestorestacionamiento.zona.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZonaCreateDTO {
    private String idZona;
    private String nombreZona;
    private String descripcion;
    private int capacidad;
}
