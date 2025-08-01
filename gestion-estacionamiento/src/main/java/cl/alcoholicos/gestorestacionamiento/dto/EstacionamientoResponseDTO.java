package cl.alcoholicos.gestorestacionamiento.dto;

import java.util.List;

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

    private EstadoEstacionamientoBasicDTO estadoEstacionamiento;

    private SensorBasicDTO sensor;

    private List<EspacioFavoritoBasicDTO> espacioFavoritos;

    private TipoEstacionamientoBasicDTO tipoEstacionamiento;

}
