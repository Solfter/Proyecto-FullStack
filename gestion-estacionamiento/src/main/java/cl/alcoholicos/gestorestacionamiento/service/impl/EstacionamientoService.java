package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.EstadoEstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.entity.SensorEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.EstacionamientoMapper;
import cl.alcoholicos.gestorestacionamiento.repository.EstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.repository.EstadoEstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.repository.SensorRepository;
import cl.alcoholicos.gestorestacionamiento.service.IEstacionamiento;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstacionamientoService implements IEstacionamiento {

    private final EstacionamientoRepository estacionamientoRepository;
    private final EstacionamientoMapper estacionamientoMapper;
    private final EstadoEstacionamientoRepository estadoEstacionamientoRepository;
    private final SensorRepository sensorRepository;

    @Override
    public EstacionamientoResponseDTO insert(EstacionamientoCreateDTO estacionamientoCreateDTO) {
        EstacionamientoEntity estacionamiento = estacionamientoMapper.toEntity(estacionamientoCreateDTO);

        EstadoEstacionamientoEntity estadoEstacionamiento = estadoEstacionamientoRepository
                .findByDescEstadoEstacionamiento(estacionamientoCreateDTO.getDescEstadoEstacionamiento())
                .orElseThrow(() -> new EntityNotFoundException("Estado de Estacionamiento no encontrado"));

        SensorEntity sensor = sensorRepository.findByNroSensor(estacionamientoCreateDTO.getNroSensor())
                .orElseThrow(() -> new EntityNotFoundException("Sensor no encontrado"));

        estacionamiento.setSensor(sensor);
        estacionamiento.setEstadoEstacionamiento(estadoEstacionamiento);

        EstacionamientoEntity estacionamientoGuardado = estacionamientoRepository.save(estacionamiento);
        EstacionamientoResponseDTO responseDTO = estacionamientoMapper.toResponseDTO(estacionamientoGuardado);
        return responseDTO;
    }

    @Override
    public EstacionamientoResponseDTO update(int nroEstacionamiento,
            EstacionamientoUpdateDTO estacionamientoUpdateDTO) {
        return estacionamientoRepository.findByNroEstacionamiento(nroEstacionamiento)
                .map(estacionamiento -> {
                    // Mapea campos simples
                    estacionamientoMapper.updateFromUpdateDTO(estacionamientoUpdateDTO, estacionamiento);

                    // 🔴 Carga la entidad EstadoEstacionamiento desde la BD
                    EstadoEstacionamientoEntity estado = estadoEstacionamientoRepository
                            .findByDescEstadoEstacionamiento(estacionamientoUpdateDTO.getDescEstadoEstacionamiento())
                            .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

                    estacionamiento.setEstadoEstacionamiento(estado);

                    EstacionamientoEntity actualizado = estacionamientoRepository.save(estacionamiento);
                    return estacionamientoMapper.toResponseDTO(actualizado);
                })
                .orElse(null);
    }

    @Override
    public boolean delete(int idEstacionamiento) {
        if (estacionamientoRepository.existsById(idEstacionamiento)) {
            estacionamientoRepository.deleteById(idEstacionamiento);
            return true;
        }
        return false;
    }

    @Override
    public EstacionamientoResponseDTO getById(int idEstacionamiento) {
        return estacionamientoRepository.findById(idEstacionamiento)
                .map(estacionamientoMapper::toResponseDTO)
                .orElse(null);
    }

    @Override
    public List<EstacionamientoResponseDTO> getAll() {
        return estacionamientoRepository.findAll().stream() // Recorre la lista
                .map(estacionamientoMapper::toResponseDTO) // Convierte a ResponseDTO
                .collect(Collectors.toList()); // Crea una nueva lista de lo que convierte
    }

    @Override
    public List<EstacionamientoResponseDTO> obtenerEstacionamientoPorEstado(String estado) {
        return estacionamientoRepository.findByEstadoEstacionamientoDescEstadoEstacionamiento(estado).stream()
                .map(estacionamientoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}
