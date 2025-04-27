package cl.alcoholicos.gestorestacionamiento.estacionamiento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.estacionamiento.entity.EstacionamientoEntity;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.repository.EstacionamientoRepository;
import cl.alcoholicos.gestorestacionamiento.estacionamiento.service.IEstacionamiento;

@Service
public class EstacionamientoService implements IEstacionamiento {

    @Autowired
    private EstacionamientoRepository estacionamientoRepository;

    @Override
    public EstacionamientoEntity insert(EstacionamientoEntity estacionamiento) {
        return estacionamientoRepository.save(estacionamiento);
    }

    @Override
    public EstacionamientoEntity update(Integer idEstacionamiento, EstacionamientoEntity estacionamiento) {
        estacionamiento.setIdEstacionamiento(idEstacionamiento);
        return estacionamientoRepository.save(estacionamiento);
    }

    @Override
    public EstacionamientoEntity delete(Integer idEstacionamiento) {
        estacionamientoRepository.deleteById(idEstacionamiento);
        return null;
    }

    @Override
    public EstacionamientoEntity getById(Integer idEstacionamiento) {
        return estacionamientoRepository.findById(idEstacionamiento).get();
    }

    @Override
    public List<EstacionamientoEntity> getAll() {
        return (List<EstacionamientoEntity>) estacionamientoRepository.findAll();
    }

}
