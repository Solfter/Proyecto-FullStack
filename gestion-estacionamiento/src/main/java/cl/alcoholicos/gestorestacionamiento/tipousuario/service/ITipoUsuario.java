package cl.alcoholicos.gestorestacionamiento.tipousuario.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.tipousuario.entity.TipoUsuarioEntity;

public interface ITipoUsuario {
       
    TipoUsuarioEntity insert(TipoUsuarioEntity usuario);

    TipoUsuarioEntity update(Integer idTipoUsuario, TipoUsuarioEntity usuario);

    TipoUsuarioEntity delete(Integer idTipoUsuario);

    TipoUsuarioEntity getById(Integer idTipoUsuario);

    List<TipoUsuarioEntity> getAll();

}
