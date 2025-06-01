package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.SensorCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.SensorResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.SensorMapper;
import cl.alcoholicos.gestorestacionamiento.repository.EstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.repository.SensorRepository;
import cl.alcoholicos.gestorestacionamiento.service.ISensor;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SensorService implements ISensor {

    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;
    private final EstacionamientoRepository estacionamientoRepository;


    @Override
    public SensorResponseDTO insert(SensorCreateDTO createDTO) {
        SensorEntity sensor = sensorMapper.toEntity(createDTO);

        EstacionamientoEntity estacionamiento = estacionamientoRepository.findById(createDTO.getIdEstacionamiento())
                        .orElseThrow(() -> new EntityNotFoundException("Tipo de Usuario no encontrado"));
        sensor.setEstacionamientos(estacionamiento);
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
