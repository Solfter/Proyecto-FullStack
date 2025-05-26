package cl.alcoholicos.gestorestacionamiento.dto;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.entity.EspacioFavoritoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoEstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;
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

}
