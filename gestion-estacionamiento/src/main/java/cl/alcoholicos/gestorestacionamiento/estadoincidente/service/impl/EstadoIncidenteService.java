package cl.alcoholicos.gestorestacionamiento.estadoincidente.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.estadoincidente.dto.EstadoIncidenteDTO;
import cl.alcoholicos.gestorestacionamiento.estadoincidente.repository.EstadoIncidenteRepository;
import cl.alcoholicos.gestorestacionamiento.estadoincidente.service.IEstadoIncidente;

@Service
public class EstadoIncidenteService implements IEstadoIncidente {

    @Autowired
    private EstadoIncidenteRepository estadoIncidenteRepository;

    @Override
    public EstadoIncidenteDTO insert(EstadoIncidenteDTO estadoIncidente) {
        return estadoIncidenteRepository.save(estadoIncidente);
    }

    @Override
    public EstadoIncidenteDTO update(Integer idEstadoIncidente, EstadoIncidenteDTO estadoIncidente) {
        estadoIncidente.setIdEstadoIncidente(idEstadoIncidente);
        return estadoIncidenteRepository.save(estadoIncidente);
    }

    @Override
    public EstadoIncidenteDTO delete(Integer idEstadoIncidente) {
        estadoIncidenteRepository.deleteById(idEstadoIncidente);
        return null;
    }

    @Override
    public EstadoIncidenteDTO getById(Integer idEstadoIncidente) {
        return estadoIncidenteRepository.findById(idEstadoIncidente).get();
    }

    @Override
    public List<EstadoIncidenteDTO> getAll() {
        return (List<EstadoIncidenteDTO>) estadoIncidenteRepository.findAll();
    }

}
