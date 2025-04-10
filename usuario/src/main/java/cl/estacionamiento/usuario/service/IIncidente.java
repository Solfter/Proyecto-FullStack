package cl.estacionamiento.usuario.service;

import java.util.List;

import cl.estacionamiento.usuario.dto.IncidenteDTO;

public interface IIncidente {
       IncidenteDTO insert(IncidenteDTO incidente);

    IncidenteDTO update(Integer idIncidente, IncidenteDTO incidente);

    IncidenteDTO delete(Integer idIncidente);

    IncidenteDTO getById(Integer idIncidente);

    List<IncidenteDTO> getAll();

}
