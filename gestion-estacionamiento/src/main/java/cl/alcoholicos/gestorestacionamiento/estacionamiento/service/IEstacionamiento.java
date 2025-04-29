package cl.alcoholicos.gestorestacionamiento.estacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoResponseDTO;

public interface IEstacionamiento{

    EstacionamientoResponseDTO getById(Integer idEstacionamiento);

    List<EstacionamientoResponseDTO> getAll();

}
