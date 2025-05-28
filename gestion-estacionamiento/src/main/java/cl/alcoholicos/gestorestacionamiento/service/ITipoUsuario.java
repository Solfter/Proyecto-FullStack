package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.TipoUsuarioResponseDTO;

public interface ITipoUsuario {
       
    TipoUsuarioResponseDTO insert(TipoUsuarioResponseDTO usuario);

    TipoUsuarioResponseDTO update(Integer idTipoUsuario, TipoUsuarioResponseDTO usuario);

    boolean delete(Integer idTipoUsuario);

    TipoUsuarioResponseDTO getById(Integer idTipoUsuario);

    List<TipoUsuarioResponseDTO> getAll();

}
