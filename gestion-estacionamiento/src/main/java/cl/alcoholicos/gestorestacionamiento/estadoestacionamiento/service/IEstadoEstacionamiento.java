package cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.estadoestacionamiento.entity.EstadoEstacionamientoEntity;

public interface IEstadoEstacionamiento {
    
    EstadoEstacionamientoEntity insert(EstadoEstacionamientoEntity estadoEstacionamiento);

    EstadoEstacionamientoEntity update(Integer idEstadoEstacionamiento, EstadoEstacionamientoEntity estadoEstacionamiento);

    EstadoEstacionamientoEntity delete(Integer idEstadoEstacionamiento);

    EstadoEstacionamientoEntity getById(Integer idEstadoEstacionamiento);

    List<EstadoEstacionamientoEntity> getAll();

}
