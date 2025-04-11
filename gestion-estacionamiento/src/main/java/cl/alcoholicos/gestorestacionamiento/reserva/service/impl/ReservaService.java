package cl.alcoholicos.gestorestacionamiento.reserva.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cl.alcoholicos.gestorestacionamiento.reserva.dto.ReservaDTO;
import cl.alcoholicos.gestorestacionamiento.reserva.repository.ReservaRepository;
import cl.alcoholicos.gestorestacionamiento.reserva.service.IReserva;

public class ReservaService implements IReserva {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public ReservaDTO insert(ReservaDTO reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public ReservaDTO update(Integer idReserva, ReservaDTO reserva) {
        reserva.setIdReserva(idReserva);
        return reservaRepository.save(reserva);
    }

    @Override
    public ReservaDTO delete(Integer idReserva) {
        reservaRepository.deleteById(idReserva);
        return null;
    }

    @Override
    public ReservaDTO getById(Integer idReserva) {
        return reservaRepository.findById(idReserva).get();
    }

    @Override
    public List<ReservaDTO> getAll() {
        return (List<ReservaDTO>) reservaRepository.findAll();

    }

}
