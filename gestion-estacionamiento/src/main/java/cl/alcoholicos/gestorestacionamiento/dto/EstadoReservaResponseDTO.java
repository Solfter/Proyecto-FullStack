package cl.alcoholicos.gestorestacionamiento.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoReservaResponseDTO {

    private int idEstadoReserva;

    private TipoEstadoReservaBasicDTO tipoEstadoReserva;

    private ReservaBasicDTO reserva;

    private LocalDateTime fechaEstadoReserva;
    
}
