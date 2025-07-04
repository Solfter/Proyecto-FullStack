package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoIncidenteResponseDTO;
import cl.alcoholicos.gestorestacionamiento.mapper.TipoEstadoIncidenteMapper;
import cl.alcoholicos.gestorestacionamiento.repository.TipoEstadoIncidenteRepository;
import cl.alcoholicos.gestorestacionamiento.service.ITipoEstadoIncidente;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TipoEstadoIncidenteService implements ITipoEstadoIncidente {

    private final TipoEstadoIncidenteRepository tipoEstadoIncidenteRepository; 
    private final TipoEstadoIncidenteMapper tipoEstadoIncidenteMapper;

    @Override
    public List<TipoEstadoIncidenteResponseDTO> getAll() {
        return tipoEstadoIncidenteRepository.findAll().stream()
            .map(tipoEstadoIncidenteMapper::toResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    public TipoEstadoIncidenteResponseDTO getById(Integer idTipoEstadoIncidente) {
        return tipoEstadoIncidenteRepository.findById(idTipoEstadoIncidente) 
            .map(tipoEstadoIncidenteMapper::toResponseDTO)
            .orElse(null);  
    }

    @Override
    public boolean delete(Integer idTipoEstadoIncidente) {
        if (tipoEstadoIncidenteRepository.existsById(idTipoEstadoIncidente)) {
            tipoEstadoIncidenteRepository.deleteById(idTipoEstadoIncidente);
            return true;
        }
        return false;
    }

}
