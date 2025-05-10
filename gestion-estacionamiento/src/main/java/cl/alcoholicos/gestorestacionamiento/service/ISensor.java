package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;

public interface ISensor {

    SensorEntity insert(SensorEntity sensor);

    SensorEntity update(Integer idSensor, SensorEntity sensor);

    SensorEntity delete(Integer idSensor);

    SensorEntity getById(Integer idSensor);

    List<SensorEntity> getAll();

}
