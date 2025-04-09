package cl.estacionamiento.usuario.service;

import java.util.List;

import cl.estacionamiento.usuario.dto.EstadoReservaDTO;

public interface IEstadoReserva {
               EstadoReservaDTO insert(EstadoReservaDTO estadoReserva);

    EstadoReservaDTO update(Integer idEstadoReserva, EstadoReservaDTO estadoReserva);

    EstadoReservaDTO delete(Integer idEstadoReserva);

    EstadoReservaDTO getById(Integer idEstadoReserva);

    List<EstadoReservaDTO> getAll();
    
    

}
