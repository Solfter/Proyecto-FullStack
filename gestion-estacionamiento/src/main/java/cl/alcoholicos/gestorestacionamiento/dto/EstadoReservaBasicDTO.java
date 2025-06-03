package cl.alcoholicos.gestorestacionamiento.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoReservaBasicDTO {

    private int idEstadoReserva;

    private LocalDate fechaEstadoReserva;
    
}
