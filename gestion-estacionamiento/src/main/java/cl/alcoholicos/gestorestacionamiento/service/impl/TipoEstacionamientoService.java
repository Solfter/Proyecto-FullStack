package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstacionamientoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.TipoEstacionamientoMapper;
import cl.alcoholicos.gestorestacionamiento.repository.TipoEstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.service.ITipoEstacionamiento;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TipoEstacionamientoService implements ITipoEstacionamiento {

    private final TipoEstacionamientoRepository tipoEstacionamientoRepository;
    private final TipoEstacionamientoMapper tipoEstacionamientoMapper;

    @Override
    public TipoEstacionamientoResponseDTO insert(TipoEstacionamientoCreateDTO createDTO) {
        TipoEstacionamientoEntity tipoEstacionamiento = tipoEstacionamientoMapper.toEntity(createDTO);
        TipoEstacionamientoEntity tipoEstacionamientoGuardado = tipoEstacionamientoRepository.save(tipoEstacionamiento);
        TipoEstacionamientoResponseDTO responseDTO = tipoEstacionamientoMapper.toResponseDTO(tipoEstacionamientoGuardado);
        return responseDTO;
        
    }

    @Override
    public List<TipoEstacionamientoResponseDTO> getAll() {
        return tipoEstacionamientoRepository.findAll().stream()
                .map(tipoEstacionamientoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}
