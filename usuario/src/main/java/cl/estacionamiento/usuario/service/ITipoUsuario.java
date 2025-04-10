package cl.estacionamiento.usuario.service;

import java.util.List;

import cl.estacionamiento.usuario.dto.TipoUsuarioDTO;

public interface ITipoUsuario {
       
        TipoUsuarioDTO insert(TipoUsuarioDTO usuario);

    TipoUsuarioDTO update(Integer idTipoUsuario, TipoUsuarioDTO usuario);

    TipoUsuarioDTO delete(Integer idTipoUsuario);

    TipoUsuarioDTO getById(Integer idTipoUsuario);

    List<TipoUsuarioDTO> getAll();

}
