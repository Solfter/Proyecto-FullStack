package cl.alcoholicos.gestorestacionamiento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoEstadoReservaResponseDTO {

    private int idTipoEstadoReserva;
    
    private String descTipoEstadoReserva;

}
