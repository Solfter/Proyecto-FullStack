package cl.alcoholicos.gestorestacionamiento.dto;

import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;
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

    private SensorEntity sensor;

}
