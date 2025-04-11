package cl.alcoholicos.gestorestacionamiento.tipousuario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cl.alcoholicos.gestorestacionamiento.tipousuario.dto.TipoUsuarioDTO;
import cl.alcoholicos.gestorestacionamiento.tipousuario.repository.TipoUsuarioRepository;
import cl.alcoholicos.gestorestacionamiento.tipousuario.service.ITipoUsuario;

public class TipoUsuarioService implements ITipoUsuario {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Override
    public TipoUsuarioDTO insert(TipoUsuarioDTO usuario) {
        return tipoUsuarioRepository.save(usuario);
    }

    @Override
    public TipoUsuarioDTO update(Integer idTipoUsuario, TipoUsuarioDTO usuario) {
        usuario.setIdTipoUsuario(idTipoUsuario);
        return tipoUsuarioRepository.save(usuario);
    }

    @Override
    public TipoUsuarioDTO delete(Integer idTipoUsuario) {
        tipoUsuarioRepository.deleteById(idTipoUsuario);
        return null;
    }

    @Override
    public TipoUsuarioDTO getById(Integer idTipoUsuario) {
        return tipoUsuarioRepository.findById(idTipoUsuario).get();
    }

    @Override
    public List<TipoUsuarioDTO> getAll() {
        return (List<TipoUsuarioDTO>) tipoUsuarioRepository.findAll();
    }

}
