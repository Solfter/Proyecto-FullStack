package cl.alcoholicos.gestorestacionamiento.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoIncidenteBasicDTO {

    private TipoEstadoIncidenteBasicDTO tipoEstadoIncidente;

    private LocalDate fechaEstadoIncidente;
}
