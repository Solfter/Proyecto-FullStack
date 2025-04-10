package cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.dto.TipoEstadoReservaDTO;

public interface ITipoEstadoReserva {

    TipoEstadoReservaDTO insert(TipoEstadoReservaDTO tipoEstadoReserva);

    TipoEstadoReservaDTO update(Integer idTipoEstadoReserva, TipoEstadoReservaDTO tipoEstadoReserva);

    TipoEstadoReservaDTO delete(Integer idTipoEstadoReserva);

    TipoEstadoReservaDTO getById(Integer idTipoEstadoReserva);

    List<TipoEstadoReservaDTO> getAll();


}
