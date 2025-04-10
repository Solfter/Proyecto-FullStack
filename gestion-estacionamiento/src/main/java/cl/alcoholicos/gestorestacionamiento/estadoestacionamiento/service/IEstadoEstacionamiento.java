package cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.dto.EstadoEstacionamientoDTO;

public interface IEstadoEstacionamiento {
    
    EstadoEstacionamientoDTO insert(EstadoEstacionamientoDTO estadoEstacionamiento);

    EstadoEstacionamientoDTO update(Integer idEstadoEstacionamiento, EstadoEstacionamientoDTO estadoEstacionamiento);

    EstadoEstacionamientoDTO delete(Integer idEstadoEstacionamiento);

    EstadoEstacionamientoDTO getById(Integer idEstadoEstacionamiento);

    List<EstadoEstacionamientoDTO> getAll();

}
