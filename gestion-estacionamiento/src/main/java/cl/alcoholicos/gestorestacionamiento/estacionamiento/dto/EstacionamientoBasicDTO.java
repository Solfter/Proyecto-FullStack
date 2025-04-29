package cl.alcoholicos.gestorestacionamiento.estacionamiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstacionamientoBasicDTO {
    private int nroEstacionamiento;
    private int idEstadoEstacionamiento;
    private String idZona;
}
