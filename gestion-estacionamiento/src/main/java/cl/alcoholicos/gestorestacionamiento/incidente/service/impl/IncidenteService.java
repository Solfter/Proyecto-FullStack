package cl.alcoholicos.gestorestacionamiento.incidente.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.incidente.dto.IncidenteDTO;
import cl.alcoholicos.gestorestacionamiento.incidente.repository.IncidenteRepository;
import cl.alcoholicos.gestorestacionamiento.incidente.service.IIncidente;

@Service
public class IncidenteService implements IIncidente {

    @Autowired
    private IncidenteRepository incidenteRepository;

    @Override
    public IncidenteDTO insert(IncidenteDTO incidente) {
        return incidenteRepository.save(incidente);
    }

    @Override
    public IncidenteDTO update(Integer idIncidente, IncidenteDTO incidente) {
        incidente.setIdIncidente(idIncidente);
        return incidenteRepository.save(incidente);
    }

    @Override
    public IncidenteDTO delete(Integer idIncidente) {
        incidenteRepository.deleteById(idIncidente);
        return null;
    }

    @Override
    public IncidenteDTO getById(Integer idIncidente) {
        return incidenteRepository.findById(idIncidente).get();
    }

    @Override
    public List<IncidenteDTO> getAll() {
        return (List<IncidenteDTO>) incidenteRepository.findAll();
    }

}
