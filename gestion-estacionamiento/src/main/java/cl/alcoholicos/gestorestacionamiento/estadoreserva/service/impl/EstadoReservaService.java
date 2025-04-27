package cl.alcoholicos.gestorestacionamiento.estadoreserva.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cl.alcoholicos.gestorestacionamiento.estadoreserva.entity.EstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.estadoreserva.repository.EstadoReservaRepository;
import cl.alcoholicos.gestorestacionamiento.estadoreserva.service.IEstadoReserva;

public class EstadoReservaService implements IEstadoReserva{

    @Autowired
    private EstadoReservaRepository estadoReservaRepository;

    @Override
    public EstadoReservaEntity insert(EstadoReservaEntity estadoReserva) {
        return estadoReservaRepository.save(estadoReserva);
    }

    @Override
    public EstadoReservaEntity update(Integer idEstadoReserva, EstadoReservaEntity estadoReserva) {
        estadoReserva.setIdReserva(idEstadoReserva);
        return estadoReservaRepository.save(estadoReserva);
    }

    @Override
    public EstadoReservaEntity delete(Integer idEstadoReserva) {
        estadoReservaRepository.deleteById(idEstadoReserva);
        return null;
    }

    @Override
    public EstadoReservaEntity getById(Integer idEstadoReserva) {
        return estadoReservaRepository.findById(idEstadoReserva).get();
    }

    @Override
    public List<EstadoReservaEntity> getAll() {
        return (List<EstadoReservaEntity>) estadoReservaRepository.findAll();
    }

}
