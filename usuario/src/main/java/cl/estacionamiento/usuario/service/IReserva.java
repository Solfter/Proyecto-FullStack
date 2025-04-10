package cl.estacionamiento.usuario.service;

import java.util.List;

import cl.estacionamiento.usuario.dto.ReservaDTO;

public interface IReserva {

           ReservaDTO insert(ReservaDTO reserva);

    ReservaDTO update(Integer idReserva, ReservaDTO reserva);

    ReservaDTO delete(Integer idReserva);

    ReservaDTO getById(Integer idReserva);

    List<ReservaDTO> getAll();

}
