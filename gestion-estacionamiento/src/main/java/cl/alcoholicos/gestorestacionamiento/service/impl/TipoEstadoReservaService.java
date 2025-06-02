package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.mapper.TipoEstadoReservaMapper;
import cl.alcoholicos.gestorestacionamiento.repository.TipoEstadoReservaRepository;
import cl.alcoholicos.gestorestacionamiento.service.ITipoEstadoReserva;
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

}
