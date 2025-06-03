package cl.alcoholicos.gestorestacionamiento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoIncidenteResponseDTO {

    private int idEstadoIncidente;

    private TipoEstadoIncidenteBasicDTO tipoEstadoIncidente;

    private IncidenteBasicDTO incidente;

}


