package cl.alcoholicos.gestorestacionamiento.estadoreserva.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cl.alcoholicos.gestorestacionamiento.estadoreserva.dto.EstadoReservaDTO;
import cl.alcoholicos.gestorestacionamiento.estadoreserva.repository.EstadoReservaRepository;
import cl.alcoholicos.gestorestacionamiento.estadoreserva.service.IEstadoReserva;

public class EstadoReservaService implements IEstadoReserva{

    @Autowired
    private EstadoReservaRepository estadoReservaRepository;

    @Override
    public EstadoReservaDTO insert(EstadoReservaDTO estadoReserva) {
        return estadoReservaRepository.save(estadoReserva);
    }

    @Override
    public EstadoReservaDTO update(Integer idEstadoReserva, EstadoReservaDTO estadoReserva) {
        estadoReserva.setIdReserva(idEstadoReserva);
        return estadoReservaRepository.save(estadoReserva);
    }

    @Override
    public EstadoReservaDTO delete(Integer idEstadoReserva) {
        estadoReservaRepository.deleteById(idEstadoReserva);
        return null;
    }

    @Override
    public EstadoReservaDTO getById(Integer idEstadoReserva) {
        return estadoReservaRepository.findById(idEstadoReserva).get();
    }

    @Override
    public List<EstadoReservaDTO> getAll() {
        return (List<EstadoReservaDTO>) estadoReservaRepository.findAll();
    }

}
