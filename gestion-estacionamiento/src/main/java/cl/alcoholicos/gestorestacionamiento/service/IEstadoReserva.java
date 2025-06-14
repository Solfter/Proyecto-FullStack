package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.EstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;

public interface IEstadoReserva {

    List<EstadoReservaResponseDTO> getByIdReserva(Integer idEstadoReserva);

    List<EstadoReservaResponseDTO> getAll();

    void crearEstadoInicial (ReservaEntity reserva);

}
