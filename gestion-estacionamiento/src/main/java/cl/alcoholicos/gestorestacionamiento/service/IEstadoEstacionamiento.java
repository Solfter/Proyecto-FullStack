package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.EstadoEstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstadoEstacionamientoResponseDTO;

public interface IEstadoEstacionamiento {
    
    EstadoEstacionamientoResponseDTO getById(Integer idEstadoEstacionamiento);

    List<EstadoEstacionamientoResponseDTO> getAll();

    EstadoEstacionamientoResponseDTO insert(EstadoEstacionamientoCreateDTO createDTO);

}
