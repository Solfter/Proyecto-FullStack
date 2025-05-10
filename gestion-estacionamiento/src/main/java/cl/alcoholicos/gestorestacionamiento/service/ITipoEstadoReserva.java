package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoReservaEntity;

public interface ITipoEstadoReserva {

    TipoEstadoReservaEntity insert(TipoEstadoReservaEntity tipoEstadoReserva);

    TipoEstadoReservaEntity update(Integer idTipoEstadoReserva, TipoEstadoReservaEntity tipoEstadoReserva);

    TipoEstadoReservaEntity delete(Integer idTipoEstadoReserva);

    TipoEstadoReservaEntity getById(Integer idTipoEstadoReserva);

    List<TipoEstadoReservaEntity> getAll();


}
