package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.EstacionamientoUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.EstacionamientoMapper;
import cl.alcoholicos.gestorestacionamiento.repository.EstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.service.IEstacionamiento;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstacionamientoService implements IEstacionamiento {

    private final EstacionamientoRepository estacionamientoRepository;
    private final EstacionamientoMapper estacionamientoMapper;

    @Override
    public EstacionamientoResponseDTO insert(EstacionamientoCreateDTO estacionamientoCreateDTO) {
        EstacionamientoEntity estacionamiento = estacionamientoMapper.toEntity(estacionamientoCreateDTO);
        EstacionamientoEntity estacionamientoGuardado = estacionamientoRepository.save(estacionamiento);
        EstacionamientoResponseDTO responseDTO = estacionamientoMapper.toResponseDTO(estacionamientoGuardado);
        return responseDTO;
    }

    @Override
    public EstacionamientoResponseDTO update(int idEstacionamiento, EstacionamientoUpdateDTO estacionamientoUpdateDTO) {
        return estacionamientoRepository.findById(idEstacionamiento)
                .map(estacionamientoExistente -> {
                    estacionamientoMapper.updateFromUpdateDTO(estacionamientoUpdateDTO, estacionamientoExistente);
                    EstacionamientoEntity estacionamientoActualizado = estacionamientoRepository.save(estacionamientoExistente);
                    return estacionamientoMapper.toResponseDTO(estacionamientoActualizado);
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

}
