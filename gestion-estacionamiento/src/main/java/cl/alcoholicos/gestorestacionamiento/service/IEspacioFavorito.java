package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.entity.EspacioFavoritoEntity;

public interface IEspacioFavorito {
    
    EspacioFavoritoEntity insert(EspacioFavoritoEntity espacioFavorito);

    EspacioFavoritoEntity update(Integer rutUsuario, Integer idEstacionamiento, EspacioFavoritoEntity espacioFavorito);

    EspacioFavoritoEntity delete(Integer rutUsuario, Integer idEstacionamiento);

    EspacioFavoritoEntity getById(Integer rutUsuario, Integer idEstacionamiento);

    List<EspacioFavoritoEntity> getAll();

}
