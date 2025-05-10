package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.ZonaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ZonaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ZonaUpdateDTO;

public interface IZona {

    ZonaResponseDTO insert(ZonaCreateDTO zona);

    ZonaResponseDTO update(String idZona, ZonaUpdateDTO zona);

    boolean delete(String idZona);

    ZonaResponseDTO getById(String idZona);

    List<ZonaResponseDTO> getAll();

}
