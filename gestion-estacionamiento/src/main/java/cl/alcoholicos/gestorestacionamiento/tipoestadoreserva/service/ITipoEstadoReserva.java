package cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.entity.TipoEstadoReservaEntity;

public interface ITipoEstadoReserva {

    TipoEstadoReservaEntity insert(TipoEstadoReservaEntity tipoEstadoReserva);

    TipoEstadoReservaEntity update(Integer idTipoEstadoReserva, TipoEstadoReservaEntity tipoEstadoReserva);

    TipoEstadoReservaEntity delete(Integer idTipoEstadoReserva);

    TipoEstadoReservaEntity getById(Integer idTipoEstadoReserva);

    List<TipoEstadoReservaEntity> getAll();


}
