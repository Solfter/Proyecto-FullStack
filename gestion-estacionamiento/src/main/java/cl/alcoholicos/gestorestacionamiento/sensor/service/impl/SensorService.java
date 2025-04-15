package cl.alcoholicos.gestorestacionamiento.sensor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.sensor.dto.SensorDTO;
import cl.alcoholicos.gestorestacionamiento.sensor.repository.SensorRepository;
import cl.alcoholicos.gestorestacionamiento.sensor.service.ISensor;

@Service
public class SensorService implements ISensor {

    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public SensorDTO insert(SensorDTO sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public SensorDTO update(Integer idSensor, SensorDTO sensor) {
        sensor.setIdSensor(idSensor);
        return sensorRepository.save(sensor);
    }

    @Override
    public SensorDTO delete(Integer idSensor) {
        sensorRepository.deleteById(idSensor);
        return null;
    }

    @Override
    public SensorDTO getById(Integer idSensor) {
        return sensorRepository.findById(idSensor).get();
    }

    @Override
    public List<SensorDTO> getAll() {
        return (List<SensorDTO>) sensorRepository.findAll();
    }

}
