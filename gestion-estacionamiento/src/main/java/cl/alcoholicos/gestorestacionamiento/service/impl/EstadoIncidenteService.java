package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.entity.EstadoIncidenteEntity;
import cl.alcoholicos.gestorestacionamiento.repository.EstadoIncidenteRepository;
import cl.alcoholicos.gestorestacionamiento.service.IEstadoIncidente;

@Service
public class EstadoIncidenteService implements IEstadoIncidente {

    @Autowired
    private EstadoIncidenteRepository estadoIncidenteRepository;

    @Override
    public EstadoIncidenteEntity insert(EstadoIncidenteEntity estadoIncidente) {
        return estadoIncidenteRepository.save(estadoIncidente);
    }

    @Override
    public EstadoIncidenteEntity update(Integer idEstadoIncidente, EstadoIncidenteEntity estadoIncidente) {
        estadoIncidente.setIdEstadoIncidente(idEstadoIncidente);
        return estadoIncidenteRepository.save(estadoIncidente);
    }

    @Override
    public EstadoIncidenteEntity delete(Integer idEstadoIncidente) {
        estadoIncidenteRepository.deleteById(idEstadoIncidente);
        return null;
    }

    @Override
    public EstadoIncidenteEntity getById(Integer idEstadoIncidente) {
        return estadoIncidenteRepository.findById(idEstadoIncidente).get();
    }

    @Override
    public List<EstadoIncidenteEntity> getAll() {
        return (List<EstadoIncidenteEntity>) estadoIncidenteRepository.findAll();
    }

}
