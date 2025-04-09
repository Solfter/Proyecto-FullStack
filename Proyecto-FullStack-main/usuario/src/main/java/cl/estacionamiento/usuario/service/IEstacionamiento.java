package cl.estacionamiento.usuario.service;

import java.util.List;

import cl.estacionamiento.usuario.dto.EstacionamientoDTO;

public interface IEstacionamiento{

    EstacionamientoDTO insert(EstacionamientoDTO estacionamiento);

    EstacionamientoDTO update(Integer idEstacionamiento, EstacionamientoDTO estacionamiento);

    EstacionamientoDTO delete(Integer idEstacionamiento);

    EstacionamientoDTO getById(Integer idEstacionamiento);

    List<EstacionamientoDTO> getAll();

}
