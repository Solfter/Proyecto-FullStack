package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.mapper.TipoEstadoReservaMapper;
import cl.alcoholicos.gestorestacionamiento.repository.TipoEstadoReservaRepository;
import cl.alcoholicos.gestorestacionamiento.service.ITipoEstadoReserva;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TipoEstadoReservaService implements ITipoEstadoReserva {

    private final TipoEstadoReservaRepository tipoEstadoReservaRepository;
    private final TipoEstadoReservaMapper tipoEstadoReservaMapper;

    @Override
    public TipoEstadoReservaResponseDTO getById(Integer idTipoEstadoReserva) {
        return tipoEstadoReservaRepository.findById(idTipoEstadoReserva)
            .map(tipoEstadoReservaMapper::toResponseDTO)
            .orElse(null);
    }

    @Override
    public List<TipoEstadoReservaResponseDTO> getAll() {
        return tipoEstadoReservaRepository.findAll().stream()
            .map(tipoEstadoReservaMapper::toResponseDTO)
            .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public TipoEstadoReservaResponseDTO insert(TipoEstadoReservaCreateDTO createDTO) {
        TipoEstadoReservaEntity tipoEstadoReserva = tipoEstadoReservaMapper.toEntity(createDTO);

        TipoEstadoReservaEntity tipoEstadoReservaGuardado = tipoEstadoReservaRepository.save(tipoEstadoReserva);

        TipoEstadoReservaResponseDTO responseDTO = tipoEstadoReservaMapper.toResponseDTO(tipoEstadoReservaGuardado);

        return responseDTO;

    }
}
