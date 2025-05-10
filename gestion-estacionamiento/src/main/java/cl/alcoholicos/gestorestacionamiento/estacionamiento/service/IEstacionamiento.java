package cl.alcoholicos.gestorestacionamiento.estacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoUpdateDTO;

public interface IEstacionamiento{

    EstacionamientoResponseDTO insert(EstacionamientoCreateDTO estacionamiento);

    EstacionamientoResponseDTO update(int idEstacionamiento, EstacionamientoUpdateDTO estacionamiento);

    boolean delete(int idEstacionamiento);

    EstacionamientoResponseDTO getById(int idEstacionamiento);

    List<EstacionamientoResponseDTO> getAll();

}
