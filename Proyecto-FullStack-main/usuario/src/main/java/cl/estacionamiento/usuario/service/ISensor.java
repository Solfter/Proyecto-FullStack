package cl.estacionamiento.usuario.service;

import java.util.List;

import cl.estacionamiento.usuario.dto.SensorDTO;

public interface ISensor {
           SensorDTO insert(SensorDTO sensor);

    SensorDTO update(Integer idSensor, SensorDTO sensor);

    SensorDTO delete(Integer idSensor);

    SensorDTO getById(Integer idSensor);

    List<SensorDTO> getAll();

}
