package cl.alcoholicos.gestorestacionamiento.estadoincidente.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.estadoincidente.entity.EstadoIncidenteEntity;

public interface IEstadoIncidente {
    
    EstadoIncidenteEntity insert(EstadoIncidenteEntity estadoIncidente);

    EstadoIncidenteEntity update(Integer idEstadoIncidente, EstadoIncidenteEntity estadoIncidente);

    EstadoIncidenteEntity delete(Integer idEstadoIncidente);

    EstadoIncidenteEntity getById(Integer idEstadoIncidente);

    List<EstadoIncidenteEntity> getAll();

}
