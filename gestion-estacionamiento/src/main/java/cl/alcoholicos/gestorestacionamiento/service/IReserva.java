package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.entity.ReservaEntity;

public interface IReserva {

    ReservaEntity insert(ReservaEntity reserva);

    ReservaEntity update(Integer idReserva, ReservaEntity reserva);

    ReservaEntity delete(Integer idReserva);

    ReservaEntity getById(Integer idReserva);

    List<ReservaEntity> getAll();

}
