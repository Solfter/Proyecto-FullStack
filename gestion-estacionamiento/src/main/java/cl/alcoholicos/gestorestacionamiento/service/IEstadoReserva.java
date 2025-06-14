package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.EstadoReservaResponseDTO;

public interface IEstadoReserva {

    List<EstadoReservaResponseDTO> getByIdReserva(Integer idEstadoReserva);

    List<EstadoReservaResponseDTO> getAll();

}
