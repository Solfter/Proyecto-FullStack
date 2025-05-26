package cl.alcoholicos.gestorestacionamiento.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservaResponseDTO {

    private int idReserva;

    private LocalDate fechaCreacionReserva;

    private LocalDate fechaReserva;

    private LocalTime horaInicio;

    private LocalTime horaFin;
    
}

