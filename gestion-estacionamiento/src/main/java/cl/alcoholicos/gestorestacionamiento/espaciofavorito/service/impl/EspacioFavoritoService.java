package cl.alcoholicos.gestorestacionamiento.espaciofavorito.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.espaciofavorito.dto.EspacioFavoritoDTO;
import cl.alcoholicos.gestorestacionamiento.espaciofavorito.dto.EspacioFavoritoId;
import cl.alcoholicos.gestorestacionamiento.espaciofavorito.repository.EspacioFavoritoRepository;
import cl.alcoholicos.gestorestacionamiento.espaciofavorito.service.IEspacioFavorito;
import cl.alcoholicos.gestorestacionamiento.usuario.dto.UsuarioDTO;
import cl.alcoholicos.gestorestacionamiento.usuario.repository.UsuarioRepository;

@Service
public class EspacioFavoritoService implements IEspacioFavorito {

    @Autowired
    private EspacioFavoritoRepository espacioFavoritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public EspacioFavoritoDTO insert(EspacioFavoritoDTO espacioFavorito) {
        Integer rutUsuario = espacioFavorito.getUsuario().getRut();
        UsuarioDTO usuario = usuarioRepository.findById(rutUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con rut: " + rutUsuario));

        espacioFavorito.setUsuario(usuario);
        return espacioFavoritoRepository.save(espacioFavorito);
    }

    @Override
    public EspacioFavoritoDTO update(Integer rutUsuario, Integer idEstacionamiento,
            EspacioFavoritoDTO espacioFavorito) {
        UsuarioDTO usuario = usuarioRepository.findById(rutUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con rut: " + rutUsuario));

        espacioFavorito.setUsuario(usuario);
        espacioFavorito.setIdEstacionamiento(idEstacionamiento);

        return espacioFavoritoRepository.save(espacioFavorito);
    }

    @Override
public EspacioFavoritoDTO delete(Integer rutUsuario, Integer idEstacionamiento) {
    EspacioFavoritoId id = new EspacioFavoritoId(rutUsuario, idEstacionamiento);
    EspacioFavoritoDTO favorito = espacioFavoritoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el espacio favorito"));
    espacioFavoritoRepository.delete(favorito);
    return favorito;
}

@Override
public EspacioFavoritoDTO getById(Integer rutUsuario, Integer idEstacionamiento) {
    EspacioFavoritoId id = new EspacioFavoritoId(rutUsuario, idEstacionamiento);
    return espacioFavoritoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el espacio favorito"));
}

@Override
public List<EspacioFavoritoDTO> getAll() {
    return espacioFavoritoRepository.findAll();
}



}