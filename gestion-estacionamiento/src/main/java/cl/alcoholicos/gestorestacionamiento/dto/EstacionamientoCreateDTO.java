package cl.alcoholicos.gestorestacionamiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstacionamientoCreateDTO {
    
    private int nroEstacionamiento;

    private String descEstadoEstacionamiento;

    private int nroSensor;

    private String descTipoEstacionamiento;

}
