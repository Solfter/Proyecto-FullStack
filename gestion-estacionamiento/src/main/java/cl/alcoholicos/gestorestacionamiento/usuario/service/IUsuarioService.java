package cl.alcoholicos.gestorestacionamiento.usuario.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.usuario.entity.UsuarioEntity;

public interface IUsuarioService {

    UsuarioEntity insert(UsuarioEntity usuario);

    UsuarioEntity update(Integer rut, UsuarioEntity usuario);

    UsuarioEntity delete(Integer rut);

    UsuarioEntity getById(Integer rut);

    List<UsuarioEntity> getAll();
}
