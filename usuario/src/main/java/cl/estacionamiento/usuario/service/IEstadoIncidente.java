package cl.estacionamiento.usuario.service;

import java.util.List;

import cl.estacionamiento.usuario.dto.EstadoIncidenteDTO;

public interface IEstadoIncidente {
               EstadoIncidenteDTO insert(EstadoIncidenteDTO estadoIncidente);

    EstadoIncidenteDTO update(Integer idEstadoIncidente, EstadoIncidenteDTO estadoIncidente);

    EstadoIncidenteDTO delete(Integer idEstadoIncidente);

    EstadoIncidenteDTO getById(Integer idEstadoIncidente);

    List<EstadoIncidenteDTO> getAll();

}
