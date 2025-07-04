package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.entity.IncidenteEntity;
import cl.alcoholicos.gestorestacionamiento.repository.IncidenteRepository;
import cl.alcoholicos.gestorestacionamiento.service.IIncidente;

@Service
public class IncidenteService implements IIncidente {

    @Autowired
    private IncidenteRepository incidenteRepository;

    @Override
    public IncidenteEntity insert(IncidenteEntity incidente) {
        return incidenteRepository.save(incidente);
    }

    @Override
    public IncidenteEntity update(Integer idIncidente, IncidenteEntity incidente) {
        incidente.setIdIncidente(idIncidente);
        return incidenteRepository.save(incidente);
    }

    @Override
    public boolean delete(Integer idIncidente) {
        if (incidenteRepository.existsById(idIncidente)) {
            incidenteRepository.deleteById(idIncidente);
            return true;
        }
        return false;
    }

    @Override
    public IncidenteEntity getById(Integer idIncidente) {
        return incidenteRepository.findById(idIncidente).get();
    }

    @Override
    public List<IncidenteEntity> getAll() {
        return (List<IncidenteEntity>) incidenteRepository.findAll();
    }

}
