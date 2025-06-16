package cl.alcoholicos.gestorestacionamiento.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaCreacionReserva;

    private LocalDate fechaReserva;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private UsuarioBasicDTO usuario;

    private EstacionamientoBasicDTO estacionamiento;

    private List<EstadoReservaBasicDTO> estadosReservas;
    
}

