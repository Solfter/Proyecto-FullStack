package cl.alcoholicos.gestorestacionamiento.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaEstadoReserva;
    
}
