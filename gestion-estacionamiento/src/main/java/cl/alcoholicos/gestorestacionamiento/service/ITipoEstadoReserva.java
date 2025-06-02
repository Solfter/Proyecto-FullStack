package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaResponseDTO;

public interface ITipoEstadoReserva {

    TipoEstadoReservaResponseDTO getById(Integer idTipoEstadoReserva);

    List<TipoEstadoReservaResponseDTO> getAll();


}
