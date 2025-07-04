package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.entity.IncidenteEntity;

public interface IIncidente {
    
    IncidenteEntity insert(IncidenteEntity incidente);

    IncidenteEntity update(Integer idIncidente, IncidenteEntity incidente);

    boolean delete(Integer idIncidente);

    IncidenteEntity getById(Integer idIncidente);

    List<IncidenteEntity> getAll();

}
