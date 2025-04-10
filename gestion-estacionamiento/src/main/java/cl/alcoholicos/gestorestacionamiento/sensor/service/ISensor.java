package cl.alcoholicos.gestorestacionamiento.sensor.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.sensor.dto.SensorDTO;

public interface ISensor {

    SensorDTO insert(SensorDTO sensor);

    SensorDTO update(Integer idSensor, SensorDTO sensor);

    SensorDTO delete(Integer idSensor);

    SensorDTO getById(Integer idSensor);

    List<SensorDTO> getAll();

}
