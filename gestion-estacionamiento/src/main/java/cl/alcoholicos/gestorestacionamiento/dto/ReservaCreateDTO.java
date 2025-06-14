package cl.alcoholicos.gestorestacionamiento.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaCreateDTO {

    private LocalDateTime fechaCreacionReserva = LocalDateTime.now();

    private LocalDate fechaReserva;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private Integer idEstacionamiento;

    private EstadoReservaResponseDTO estadoReserva;

}
