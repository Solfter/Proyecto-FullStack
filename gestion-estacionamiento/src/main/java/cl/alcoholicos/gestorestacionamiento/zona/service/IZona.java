package cl.alcoholicos.gestorestacionamiento.zona.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaDTO;

public interface IZona {

    ZonaDTO insert(ZonaDTO zona);

    ZonaDTO update(Integer idZona, ZonaDTO zona);

    ZonaDTO delete(Integer idZona);

    ZonaDTO getById(Integer idZona);

    List<ZonaDTO> getAll();

}
