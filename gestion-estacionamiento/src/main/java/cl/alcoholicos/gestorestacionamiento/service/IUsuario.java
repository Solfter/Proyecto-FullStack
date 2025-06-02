package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.UsuarioCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.UsuarioUpdateDTO;

public interface IUsuario {

    UsuarioResponseDTO insert(UsuarioCreateDTO usuarioCreateDTO);

    UsuarioResponseDTO update(Integer rut, UsuarioUpdateDTO usuario);

    boolean delete(Integer rut);

    UsuarioResponseDTO getById(Integer rut);

    List<UsuarioResponseDTO> getAll();
}
