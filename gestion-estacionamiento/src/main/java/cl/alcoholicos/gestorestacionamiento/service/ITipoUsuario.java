package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.entity.TipoUsuarioEntity;

public interface ITipoUsuario {
       
    TipoUsuarioEntity insert(TipoUsuarioEntity usuario);

    TipoUsuarioEntity update(Integer idTipoUsuario, TipoUsuarioEntity usuario);

    TipoUsuarioEntity delete(Integer idTipoUsuario);

    TipoUsuarioEntity getById(Integer idTipoUsuario);

    List<TipoUsuarioEntity> getAll();

}
