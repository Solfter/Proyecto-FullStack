package cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.entity.EstadoEstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.repository.EstadoEstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.service.IEstadoEstacionamiento;

@Service
public class EstadoEstacionamientoService implements IEstadoEstacionamiento {
    
    @Autowired
    private EstadoEstacionamientoRepository estadoEstacionamientoRepository;

    @Override
    public EstadoEstacionamientoEntity insert(EstadoEstacionamientoEntity estadoEstacionamientoDTO) {
        return estadoEstacionamientoRepository.save(estadoEstacionamientoDTO);
    }

    @Override
    public EstadoEstacionamientoEntity update(Integer idEstadoEstacionamiento, EstadoEstacionamientoEntity estadoEstacionamientoDTO) {
        estadoEstacionamientoDTO.setIdEstadoEstacionamiento(idEstadoEstacionamiento);
        return estadoEstacionamientoRepository.save(estadoEstacionamientoDTO);
    }

    @Override
    public EstadoEstacionamientoEntity delete(Integer idEstacionamiento) {
        estadoEstacionamientoRepository.deleteById(idEstacionamiento);
        return null;
    }

    @Override
    public EstadoEstacionamientoEntity getById(Integer idEstadoEstacionamiento) {
        return estadoEstacionamientoRepository.findById(idEstadoEstacionamiento).get();
    }

    @Override
    public List<EstadoEstacionamientoEntity> getAll() {
        return (List<EstadoEstacionamientoEntity>) estadoEstacionamientoRepository.findAll();
    }
}
