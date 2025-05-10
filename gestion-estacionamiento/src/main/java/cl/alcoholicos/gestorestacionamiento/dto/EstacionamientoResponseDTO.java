package cl.alcoholicos.gestorestacionamiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstacionamientoResponseDTO {
    private int idEstacionamiento;
    private int nroEstacionamiento;
    private int idEstadoEstacionamiento;
    private int idSensor;
    private String idZona;
}
