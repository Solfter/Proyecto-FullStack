package cl.alcoholicos.gestorestacionamiento.espaciofavorito.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.espaciofavorito.dto.EspacioFavoritoDTO;
import cl.alcoholicos.gestorestacionamiento.espaciofavorito.repository.EspacioFavoritoRepository;
import cl.alcoholicos.gestorestacionamiento.espaciofavorito.service.IEspacioFavorito;

@Service
public class EspacioFavoritoService implements IEspacioFavorito {

    @Autowired
    private EspacioFavoritoRepository espacioFavoritoRepository;

    @Override
    public EspacioFavoritoDTO insert(EspacioFavoritoDTO espacioFavorito) {
        return espacioFavoritoRepository.save(espacioFavorito);
    }

    @Override
    public EspacioFavoritoDTO update(Integer rutUsuario, Integer idEstacionamiento,
            EspacioFavoritoDTO espacioFavorito) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public EspacioFavoritoDTO delete(Integer rutUsuario, Integer idEstacionamiento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public EspacioFavoritoDTO getById(Integer rutUsuario, Integer idEstacionamiento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public List<EspacioFavoritoDTO> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    
}