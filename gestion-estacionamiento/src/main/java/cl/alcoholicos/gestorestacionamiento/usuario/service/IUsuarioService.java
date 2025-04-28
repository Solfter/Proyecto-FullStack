package cl.alcoholicos.gestorestacionamiento.usuario.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioCreateDTO;
import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioUpdateDTO;

public interface IUsuarioService {

    UsuarioResponseDTO insert(UsuarioCreateDTO usuarioCreateDTO);

    UsuarioResponseDTO update(Integer rut, UsuarioUpdateDTO usuario);

    boolean delete(Integer rut);

    UsuarioResponseDTO getById(Integer rut);

    List<UsuarioResponseDTO> getAll();
}
