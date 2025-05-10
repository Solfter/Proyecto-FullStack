package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.entity.EstadoReservaEntity;

public interface IEstadoReserva {
    
    EstadoReservaEntity insert(EstadoReservaEntity estadoReserva);

    EstadoReservaEntity update(Integer idEstadoReserva, EstadoReservaEntity estadoReserva);

    EstadoReservaEntity delete(Integer idEstadoReserva);

    EstadoReservaEntity getById(Integer idEstadoReserva);

    List<EstadoReservaEntity> getAll();
    
    

}
