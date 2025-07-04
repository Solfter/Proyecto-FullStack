package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstadoIncidenteResponseDTO;

public interface ITipoEstadoIncidente {

    List<TipoEstadoIncidenteResponseDTO> getAll();

    TipoEstadoIncidenteResponseDTO getById(Integer idTipoEstadoIncidente);

    boolean delete(Integer idTipoEstadoIncidente);

}
