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
    public EspacioFavoritoDTO update(Integer idEspacioFavorito, EspacioFavoritoDTO espacioFavorito) {
        espacioFavorito.setIdEstacionamiento(idEspacioFavorito);
        return espacioFavoritoRepository.save(espacioFavorito);
    }

    @Override
    public EspacioFavoritoDTO delete(Integer idEspacioFavorito) {
        espacioFavoritoRepository.deleteById(idEspacioFavorito);
        return null;
    }

    @Override
    public EspacioFavoritoDTO getById(Integer idEspacioFavorito) {
        return espacioFavoritoRepository.findById(idEspacioFavorito).get();
    }

    @Override
    public List<EspacioFavoritoDTO> getAll() {
        return (List<EspacioFavoritoDTO>) espacioFavoritoRepository.findAll();
    }

}