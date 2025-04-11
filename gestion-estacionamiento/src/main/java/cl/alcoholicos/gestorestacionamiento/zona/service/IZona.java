package cl.alcoholicos.gestorestacionamiento.zona.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaDTO;

public interface IZona {

    ZonaDTO insert(ZonaDTO zona);

    ZonaDTO update(Character idZona, ZonaDTO zona);

    ZonaDTO delete(Character idZona);

    ZonaDTO getById(Character idZona);

    List<ZonaDTO> getAll();

}
