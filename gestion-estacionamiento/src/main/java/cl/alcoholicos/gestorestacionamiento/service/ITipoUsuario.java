package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.TipoUsuarioCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.TipoUsuarioResponseDTO;

public interface ITipoUsuario {
       
    TipoUsuarioResponseDTO insert(TipoUsuarioCreateDTO usuario);

    TipoUsuarioResponseDTO update(Integer idTipoUsuario, TipoUsuarioResponseDTO usuario);

    boolean delete(Integer idTipoUsuario);

    TipoUsuarioResponseDTO getById(Integer idTipoUsuario);

    List<TipoUsuarioResponseDTO> getAll();

}
