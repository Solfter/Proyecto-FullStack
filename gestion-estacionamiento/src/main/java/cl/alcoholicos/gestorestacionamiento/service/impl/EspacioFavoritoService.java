package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.entity.EspacioFavoritoEntity;
import cl.alcoholicos.gestorestacionamiento.repository.EspacioFavoritoRepository;
import cl.alcoholicos.gestorestacionamiento.service.IEspacioFavorito;

@Service
public class EspacioFavoritoService implements IEspacioFavorito {

    @Autowired
    private EspacioFavoritoRepository espacioFavoritoRepository;

    @Override
    public EspacioFavoritoEntity insert(EspacioFavoritoEntity espacioFavorito) {
        return espacioFavoritoRepository.save(espacioFavorito);
    }

    @Override
    public EspacioFavoritoEntity update(Integer rutUsuario, Integer idEstacionamiento,
            EspacioFavoritoEntity espacioFavorito) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public EspacioFavoritoEntity delete(Integer rutUsuario, Integer idEstacionamiento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public EspacioFavoritoEntity getById(Integer rutUsuario, Integer idEstacionamiento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public List<EspacioFavoritoEntity> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    
}