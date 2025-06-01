package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.SensorCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.SensorResponseDTO;

public interface ISensor {

    SensorResponseDTO insert(SensorCreateDTO sensor);

    SensorResponseDTO update(Integer idSensor, SensorResponseDTO sensor);

    boolean delete(Integer idSensor);

    SensorResponseDTO getById(Integer idSensor);

    List<SensorResponseDTO> getAll();

}
