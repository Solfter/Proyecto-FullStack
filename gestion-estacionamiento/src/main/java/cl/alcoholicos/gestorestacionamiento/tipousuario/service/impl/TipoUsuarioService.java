package cl.alcoholicos.gestorestacionamiento.tipousuario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.tipousuario.entity.TipoUsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.tipousuario.repository.TipoUsuarioRepository;
import cl.alcoholicos.gestorestacionamiento.tipousuario.service.ITipoUsuario;

@Service
public class TipoUsuarioService implements ITipoUsuario {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Override
    public TipoUsuarioEntity insert(TipoUsuarioEntity usuario) {
        return tipoUsuarioRepository.save(usuario);
    }

    @Override
    public TipoUsuarioEntity update(Integer idTipoUsuario, TipoUsuarioEntity usuario) {
        usuario.setIdTipoUsuario(idTipoUsuario);
        return tipoUsuarioRepository.save(usuario);
    }

    @Override
    public TipoUsuarioEntity delete(Integer idTipoUsuario) {
        tipoUsuarioRepository.deleteById(idTipoUsuario);
        return null;
    }

    @Override
    public TipoUsuarioEntity getById(Integer idTipoUsuario) {
        return tipoUsuarioRepository.findById(idTipoUsuario).get();
    }

    @Override
    public List<TipoUsuarioEntity> getAll() {
        return (List<TipoUsuarioEntity>) tipoUsuarioRepository.findAll();
    }

}
