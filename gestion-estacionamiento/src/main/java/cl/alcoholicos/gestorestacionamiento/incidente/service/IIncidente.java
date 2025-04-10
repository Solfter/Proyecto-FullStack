package cl.alcoholicos.gestorestacionamiento.incidente.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.incidente.dto.IncidenteDTO;

public interface IIncidente {
    
    IncidenteDTO insert(IncidenteDTO incidente);

    IncidenteDTO update(Integer idIncidente, IncidenteDTO incidente);

    IncidenteDTO delete(Integer idIncidente);

    IncidenteDTO getById(Integer idIncidente);

    List<IncidenteDTO> getAll();

}
