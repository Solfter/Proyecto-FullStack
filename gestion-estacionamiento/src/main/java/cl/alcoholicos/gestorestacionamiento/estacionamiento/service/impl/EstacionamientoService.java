package cl.alcoholicos.gestorestacionamiento.estacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.mapper.EstacionamientoMapper;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.repository.EstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.service.IEstacionamiento;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstacionamientoService implements IEstacionamiento {

    private final EstacionamientoRepository estacionamientoRepository;
    private final EstacionamientoMapper estacionamientoMapper;

    @Override
    public EstacionamientoResponseDTO getById(Integer idEstacionamiento) {
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

}
