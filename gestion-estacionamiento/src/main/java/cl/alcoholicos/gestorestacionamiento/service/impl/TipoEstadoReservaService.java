package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoReservaEntity;
import cl.alcoholicos.gestorestacionamiento.repository.TipoEstadoReservaRepository;
import cl.alcoholicos.gestorestacionamiento.service.ITipoEstadoReserva;

@Service
public class TipoEstadoReservaService implements ITipoEstadoReserva {

    @Autowired
    private TipoEstadoReservaRepository tipoEstadoReservaRepository;
    @Override
    public TipoEstadoReservaEntity insert(TipoEstadoReservaEntity tipoEstadoReserva) {
        return tipoEstadoReservaRepository.save(tipoEstadoReserva);
    }

    @Override
    public TipoEstadoReservaEntity update(Integer idTipoEstadoReserva, TipoEstadoReservaEntity tipoEstadoReserva) {
        tipoEstadoReserva.setIdTipoEstadoReserva(idTipoEstadoReserva);
        return tipoEstadoReservaRepository.save(tipoEstadoReserva);
    }

    @Override
    public TipoEstadoReservaEntity delete(Integer idTipoEstadoReserva) {
        tipoEstadoReservaRepository.deleteById(idTipoEstadoReserva);
        return null;
    }

    @Override
    public TipoEstadoReservaEntity getById(Integer idTipoEstadoReserva) {
        return tipoEstadoReservaRepository.findById(idTipoEstadoReserva).get();
    }

    @Override
    public List<TipoEstadoReservaEntity> getAll() {
        return (List<TipoEstadoReservaEntity>) tipoEstadoReservaRepository.findAll();
    }

}
