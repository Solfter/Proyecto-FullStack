package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.EstadoEstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.mapper.EstadoEstacionamientoMapper;
import cl.alcoholicos.gestorestacionamiento.repository.EstadoEstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.service.IEstadoEstacionamiento;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstadoEstacionamientoService implements IEstadoEstacionamiento {
    
    private final EstadoEstacionamientoRepository estadoEstacionamientoRepository;
    private final EstadoEstacionamientoMapper estadoEstacionamientoMapper;

    @Override
    public EstadoEstacionamientoResponseDTO getById(Integer idEstadoEstacionamiento) {
        return estadoEstacionamientoRepository.findById(idEstadoEstacionamiento)
            .map(estadoEstacionamientoMapper::toResponseDTO)
            .orElse(null);
    }

    @Override
    public List<EstadoEstacionamientoResponseDTO> getAll() {
        return estadoEstacionamientoRepository.findAll().stream()
            .map(estadoEstacionamientoMapper::toResponseDTO)
            .collect(Collectors.toList());
    }
}
