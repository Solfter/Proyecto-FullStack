package cl.alcoholicos.gestorestacionamiento.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoEstacionamientoResponseDTO {

    private int idEstadoEstacionamiento;

    private String descEstadoEstacionamiento;

    private List<EstacionamientoBasicDTO> estacionamientos;

}
