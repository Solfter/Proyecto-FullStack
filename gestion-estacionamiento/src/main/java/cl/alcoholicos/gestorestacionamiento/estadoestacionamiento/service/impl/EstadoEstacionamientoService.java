package cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.dto.EstadoEstacionamientoDTO;
import cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.repository.EstadoEstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.service.IEstadoEstacionamiento;

public class EstadoEstacionamientoService implements IEstadoEstacionamiento {
    
    @Autowired
    private EstadoEstacionamientoRepository estadoEstacionamientoRepository;

    @Override
    public EstadoEstacionamientoDTO insert(EstadoEstacionamientoDTO estadoEstacionamientoDTO) {
        return estadoEstacionamientoRepository.save(estadoEstacionamientoDTO);
    }

    @Override
    public EstadoEstacionamientoDTO update(Integer idEstadoEstacionamiento, EstadoEstacionamientoDTO estadoEstacionamientoDTO) {
        estadoEstacionamientoDTO.setIdEstadoEstacionamiento(idEstadoEstacionamiento);
        return estadoEstacionamientoRepository.save(estadoEstacionamientoDTO);
    }

    @Override
    public EstadoEstacionamientoDTO delete(Integer idEstacionamiento) {
        estadoEstacionamientoRepository.deleteById(idEstacionamiento);
        return null;
    }

    @Override
    public EstadoEstacionamientoDTO getById(Integer idEstadoEstacionamiento) {
        return estadoEstacionamientoRepository.findById(idEstadoEstacionamiento).get();
    }

    @Override
    public List<EstadoEstacionamientoDTO> getAll() {
        return (List<EstadoEstacionamientoDTO>) estadoEstacionamientoRepository.findAll();
    }
}
