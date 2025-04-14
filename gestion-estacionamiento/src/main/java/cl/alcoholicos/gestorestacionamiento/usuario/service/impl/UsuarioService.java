package cl.alcoholicos.gestorestacionamiento.usuario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public UsuarioDTO delete(Integer rut) {
        UsuarioDTO usuario = usuarioRepository.findById(rut)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Clear espaciosFavoritos explicitly to ensure cascade works
        if (usuario.getEspaciosFavoritos() != null) {
            usuario.getEspaciosFavoritos().clear();
        }

        // Save the changes to clear the collection
        usuarioRepository.save(usuario);

        try {
            usuarioRepository.delete(usuario);
        } catch (DataIntegrityViolationException ex) {
            // We still need to catch this as there might be other relationships
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "No se puede eliminar el usuario, tiene registros asociados. Detalles: " + ex.getMessage());
        }

        return usuario;
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
