package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.TipoEstacionamientoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoEstacionamientoResponseDTO;

public interface ITipoEstacionamiento {

    TipoEstacionamientoResponseDTO insert(TipoEstacionamientoCreateDTO tipoEstacionamiento);

    List<TipoEstacionamientoResponseDTO> getAll();

}
