package cl.alcoholicos.gestorestacionamiento.estacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoDTO;

public interface IEstacionamiento{

    EstacionamientoDTO insert(EstacionamientoDTO estacionamiento);

    EstacionamientoDTO update(Integer idEstacionamiento, EstacionamientoDTO estacionamiento);

    EstacionamientoDTO delete(Integer idEstacionamiento);

    EstacionamientoDTO getById(Integer idEstacionamiento);

    List<EstacionamientoDTO> getAll();

}
