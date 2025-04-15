package cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.dto.TipoEstadoReservaDTO;
import cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.repository.TipoEstadoReservaRepository;
import cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.service.ITipoEstadoReserva;

@Service
public class TipoEstadoReservaService implements ITipoEstadoReserva {

    @Autowired
    private TipoEstadoReservaRepository tipoEstadoReservaRepository;
    @Override
    public TipoEstadoReservaDTO insert(TipoEstadoReservaDTO tipoEstadoReserva) {
        return tipoEstadoReservaRepository.save(tipoEstadoReserva);
    }

    @Override
    public TipoEstadoReservaDTO update(Integer idTipoEstadoReserva, TipoEstadoReservaDTO tipoEstadoReserva) {
        tipoEstadoReserva.setIdTipoEstadoReserva(idTipoEstadoReserva);
        return tipoEstadoReservaRepository.save(tipoEstadoReserva);
    }

    @Override
    public TipoEstadoReservaDTO delete(Integer idTipoEstadoReserva) {
        tipoEstadoReservaRepository.deleteById(idTipoEstadoReserva);
        return null;
    }

    @Override
    public TipoEstadoReservaDTO getById(Integer idTipoEstadoReserva) {
        return tipoEstadoReservaRepository.findById(idTipoEstadoReserva).get();
    }

    @Override
    public List<TipoEstadoReservaDTO> getAll() {
        return (List<TipoEstadoReservaDTO>) tipoEstadoReservaRepository.findAll();
    }

}
