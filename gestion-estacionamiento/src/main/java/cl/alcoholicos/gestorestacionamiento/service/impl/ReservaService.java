package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;
import cl.alcoholicos.gestorestacionamiento.repository.ReservaRepository;
import cl.alcoholicos.gestorestacionamiento.service.IReserva;

@Service
public class ReservaService implements IReserva {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public ReservaEntity insert(ReservaEntity reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public ReservaEntity update(Integer idReserva, ReservaEntity reserva) {
        reserva.setIdReserva(idReserva);
        return reservaRepository.save(reserva);
    }

    @Override
    public ReservaEntity delete(Integer idReserva) {
        reservaRepository.deleteById(idReserva);
        return null;
    }

    @Override
    public ReservaEntity getById(Integer idReserva) {
        return reservaRepository.findById(idReserva).get();
    }

    @Override
    public List<ReservaEntity> getAll() {
        return (List<ReservaEntity>) reservaRepository.findAll();

    }

}
