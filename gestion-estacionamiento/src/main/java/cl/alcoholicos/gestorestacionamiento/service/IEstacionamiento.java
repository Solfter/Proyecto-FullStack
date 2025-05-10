package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoUpdateDTO;

public interface IEstacionamiento{

    EstacionamientoResponseDTO insert(EstacionamientoCreateDTO estacionamiento);

    EstacionamientoResponseDTO update(int idEstacionamiento, EstacionamientoUpdateDTO estacionamiento);

    boolean delete(int idEstacionamiento);

    EstacionamientoResponseDTO getById(int idEstacionamiento);

    List<EstacionamientoResponseDTO> getAll();

}
