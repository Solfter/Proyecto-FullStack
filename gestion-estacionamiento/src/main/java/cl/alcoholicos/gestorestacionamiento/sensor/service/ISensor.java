package cl.alcoholicos.gestorestacionamiento.sensor.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.sensor.entity.SensorEntity;

public interface ISensor {

    SensorEntity insert(SensorEntity sensor);

    SensorEntity update(Integer idSensor, SensorEntity sensor);

    SensorEntity delete(Integer idSensor);

    SensorEntity getById(Integer idSensor);

    List<SensorEntity> getAll();

}
