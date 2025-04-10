package cl.alcoholicos.gestorestacionamiento.estadoincidente.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.estadoincidente.dto.EstadoIncidenteDTO;

public interface IEstadoIncidente {
    
    EstadoIncidenteDTO insert(EstadoIncidenteDTO estadoIncidente);

    EstadoIncidenteDTO update(Integer idEstadoIncidente, EstadoIncidenteDTO estadoIncidente);

    EstadoIncidenteDTO delete(Integer idEstadoIncidente);

    EstadoIncidenteDTO getById(Integer idEstadoIncidente);

    List<EstadoIncidenteDTO> getAll();

}
