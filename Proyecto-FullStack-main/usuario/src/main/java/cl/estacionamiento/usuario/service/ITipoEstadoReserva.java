package cl.estacionamiento.usuario.service;

import java.util.List;

import cl.estacionamiento.usuario.dto.TipoEstadoReservaDTO;

public interface ITipoEstadoReserva {

       TipoEstadoReservaDTO insert(TipoEstadoReservaDTO tipoEstadoReserva);

    TipoEstadoReservaDTO update(Integer idTipoEstadoReserva, TipoEstadoReservaDTO tipoEstadoReserva);

    TipoEstadoReservaDTO delete(Integer idTipoEstadoReserva);

    TipoEstadoReservaDTO getById(Integer idTipoEstadoReserva);

    List<TipoEstadoReservaDTO> getAll();


}
