package cl.alcoholicos.gestorestacionamiento.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    private UsuarioBasicDTO usuario;

    private EstacionamientoBasicDTO estacionamiento;

    private List<EstadoReservaBasicDTO> estadosReservas;
    
}

