package cl.estacionamiento.usuario.service;

import java.util.List;

import cl.estacionamiento.usuario.dto.EspacioFavoritoDTO;

public interface IEspacioFavorito {
               EspacioFavoritoDTO insert(EspacioFavoritoDTO espacioFavorito);

    EspacioFavoritoDTO update(Integer idEspacioFavorito, EspacioFavoritoDTO espacioFavorito);

    EspacioFavoritoDTO delete(Integer idEspacioFavorito);

    EspacioFavoritoDTO getById(Integer idEspacioFavorito);

    List<EspacioFavoritoDTO> getAll();

}
