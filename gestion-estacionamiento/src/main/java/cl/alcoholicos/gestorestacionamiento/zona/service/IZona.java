package cl.alcoholicos.gestorestacionamiento.zona.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaUpdateDTO;

public interface IZona {

    ZonaResponseDTO insert(ZonaCreateDTO zona);

    ZonaResponseDTO update(String idZona, ZonaUpdateDTO zona);

    boolean delete(String idZona);

    ZonaResponseDTO getById(String idZona);

    List<ZonaResponseDTO> getAll();

}
