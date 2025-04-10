package cl.estacionamiento.usuario.service;

import java.util.List;

import cl.estacionamiento.usuario.dto.ZonaDTO;

public interface IZona {

       ZonaDTO insert(ZonaDTO zona);

    ZonaDTO update(Integer idZona, ZonaDTO zona);

    ZonaDTO delete(Integer idZona);

    ZonaDTO getById(Integer idZona);

    List<ZonaDTO> getAll();

}
