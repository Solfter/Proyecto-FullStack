package cl.alcoholicos.gestorestacionamiento.usuario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.usuario.entity.UsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.usuario.repository.UsuarioRepository;
import cl.alcoholicos.gestorestacionamiento.usuario.service.IUsuarioService;


@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioEntity insert(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioEntity update(Integer rut, UsuarioEntity usuario) {
        usuario.setRut(rut);
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioEntity delete(Integer rut) {
        usuarioRepository.deleteById(rut);
        return null;
    }

    @Override
    public UsuarioEntity getById(Integer rut) {
        return usuarioRepository.findById(rut).get();
    }

    @Override
    public List<UsuarioEntity> getAll() {
        return (List<UsuarioEntity>) usuarioRepository.findAll();
    }

}
