package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.SensorCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.SensorResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.SensorMapper;
import cl.alcoholicos.gestorestacionamiento.repository.SensorRepository;
import cl.alcoholicos.gestorestacionamiento.service.ISensor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SensorService implements ISensor {

    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;


    @Override
    public SensorResponseDTO insert(SensorCreateDTO createDTO) {
        SensorEntity sensor = sensorMapper.toEntity(createDTO);
        // Guardar en BD
        SensorEntity sensorGuardado = sensorRepository.save(sensor);
        // Convertir a DTO de respuesta
        SensorResponseDTO responseDTO = sensorMapper.toResponseDTO(sensorGuardado);        
        return responseDTO;
    }

    @Override
    public SensorResponseDTO update(Integer idSensor, SensorResponseDTO sensor) {
        /*sensor.setIdSensor(idSensor);
        return sensorRepository.save(sensor); */
        return null;
    }

    @Override
    public boolean delete(Integer idSensor) {
        if (sensorRepository.existsById(idSensor)) {
            sensorRepository.deleteById(idSensor);
            return true;
        }
        return false;
    }

    @Override
    public SensorResponseDTO getById(Integer idSensor) {
        return sensorRepository.findById(idSensor)
                .map(sensorMapper::toResponseDTO)
                .orElse(null);
    }

    @Override
    public List<SensorResponseDTO> getAll() {
        return sensorRepository.findAll().stream()
            .map(sensorMapper::toResponseDTO)
            .collect(Collectors.toList());
    }

}
