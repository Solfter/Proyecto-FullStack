package cl.alcoholicos.gestorestacionamiento.usuario.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioDTO;

public interface IUsuarioService {

    UsuarioDTO insert(UsuarioDTO usuario);

    UsuarioDTO update(Integer rut, UsuarioDTO usuario);

    UsuarioDTO delete(Integer rut);

    UsuarioDTO getById(Integer rut);

    List<UsuarioDTO> getAll();
}
