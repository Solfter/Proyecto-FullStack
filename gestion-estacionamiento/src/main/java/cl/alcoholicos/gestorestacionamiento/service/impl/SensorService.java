package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;
import cl.alcoholicos.gestorestacionamiento.repository.SensorRepository;
import cl.alcoholicos.gestorestacionamiento.service.ISensor;

@Service
public class SensorService implements ISensor {

    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public SensorEntity insert(SensorEntity sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public SensorEntity update(Integer idSensor, SensorEntity sensor) {
        sensor.setIdSensor(idSensor);
        return sensorRepository.save(sensor);
    }

    @Override
    public SensorEntity delete(Integer idSensor) {
        sensorRepository.deleteById(idSensor);
        return null;
    }

    @Override
    public SensorEntity getById(Integer idSensor) {
        return sensorRepository.findById(idSensor).get();
    }

    @Override
    public List<SensorEntity> getAll() {
        return (List<SensorEntity>) sensorRepository.findAll();
    }

}
