package cl.alcoholicos.gestorestacionamiento.estacionamiento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.estacionamiento.dto.EstacionamientoDTO;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.repository.EstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.service.IEstacionamiento;

@Service
public class EstacionamientoService implements IEstacionamiento {

    @Autowired
    private EstacionamientoRepository estacionamientoRepository;

    @Override
    public EstacionamientoDTO insert(EstacionamientoDTO estacionamiento) {
        return estacionamientoRepository.save(estacionamiento);
    }

    @Override
    public EstacionamientoDTO update(Integer idEstacionamiento, EstacionamientoDTO estacionamiento) {
        estacionamiento.setIdEstacionamiento(idEstacionamiento);
        return estacionamientoRepository.save(estacionamiento);
    }

    @Override
    public EstacionamientoDTO delete(Integer idEstacionamiento) {
        estacionamientoRepository.deleteById(idEstacionamiento);
        return null;
    }

    @Override
    public EstacionamientoDTO getById(Integer idEstacionamiento) {
        return estacionamientoRepository.findById(idEstacionamiento).get();
    }

    @Override
    public List<EstacionamientoDTO> getAll() {
        return (List<EstacionamientoDTO>) estacionamientoRepository.findAll();
    }

}
