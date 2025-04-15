package cl.alcoholicos.gestorestacionamiento.usuario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioDTO;
import cl.alcoholicos.gestorestacionamiento.usuario.repository.UsuarioRepository;
import cl.alcoholicos.gestorestacionamiento.usuario.service.IUsuarioService;


@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO insert(UsuarioDTO usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioDTO update(Integer rut, UsuarioDTO usuario) {
        usuario.setRut(rut);
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioDTO delete(Integer rut) {
        usuarioRepository.deleteById(rut);
        return null;
    }

    @Override
    public UsuarioDTO getById(Integer rut) {
        return usuarioRepository.findById(rut).get();
    }

    @Override
    public List<UsuarioDTO> getAll() {
        return (List<UsuarioDTO>) usuarioRepository.findAll();
    }

}
