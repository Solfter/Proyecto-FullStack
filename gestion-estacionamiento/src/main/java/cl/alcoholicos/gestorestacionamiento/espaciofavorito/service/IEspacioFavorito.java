package cl.alcoholicos.gestorestacionamiento.espaciofavorito.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.espaciofavorito.dto.EspacioFavoritoDTO;

public interface IEspacioFavorito {
    
    EspacioFavoritoDTO insert(EspacioFavoritoDTO espacioFavorito);

    EspacioFavoritoDTO update(Integer rutUsuario, Integer idEstacionamiento, EspacioFavoritoDTO espacioFavorito);

    EspacioFavoritoDTO delete(Integer rutUsuario, Integer idEstacionamiento);

    EspacioFavoritoDTO getById(Integer rutUsuario, Integer idEstacionamiento);

    List<EspacioFavoritoDTO> getAll();

}
