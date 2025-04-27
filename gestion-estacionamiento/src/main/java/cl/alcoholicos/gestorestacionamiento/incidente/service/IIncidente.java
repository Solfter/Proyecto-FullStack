package cl.alcoholicos.gestorestacionamiento.incidente.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.incidente.entity.IncidenteEntity;

public interface IIncidente {
    
    IncidenteEntity insert(IncidenteEntity incidente);

    IncidenteEntity update(Integer idIncidente, IncidenteEntity incidente);

    IncidenteEntity delete(Integer idIncidente);

    IncidenteEntity getById(Integer idIncidente);

    List<IncidenteEntity> getAll();

}
