package cl.alcoholicos.gestorestacionamiento.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoEstacionamientoResponseDTO {

    private Integer idTipoEstacionamiento;

    private String descTipoEstacionamiento;

    private List<EstacionamientoResponseDTO> estacionamientos;

}
