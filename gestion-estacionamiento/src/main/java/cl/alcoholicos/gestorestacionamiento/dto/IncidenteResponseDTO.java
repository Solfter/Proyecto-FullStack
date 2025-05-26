package cl.alcoholicos.gestorestacionamiento.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidenteResponseDTO {

    private int idIncidente;

    private Date fechaIncidente;

    private String descripcion;
    
}
