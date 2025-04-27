package cl.alcoholicos.gestorestacionamiento.marca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.marca.entity.MarcaEntity;
import cl.alcoholicos.gestorestacionamiento.marca.repository.MarcaRepository;
import cl.alcoholicos.gestorestacionamiento.marca.service.IMarca;

@Service
public class MarcaService implements IMarca {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public MarcaEntity insert(MarcaEntity marca) {
        return marcaRepository.save(marca);
    }

    @Override
    public MarcaEntity update(Integer idMarca, MarcaEntity marca) {
        marca.setIdMarca(idMarca);
        return marcaRepository.save(marca);
    }

    @Override
    public MarcaEntity delete(Integer idMarca) {
        marcaRepository.deleteById(idMarca);
        return null;
    }

    @Override
    public MarcaEntity getById(Integer idMarca) {
        return marcaRepository.findById(idMarca).get();
    }

    @Override
    public List<MarcaEntity> getAll() {
        return (List<MarcaEntity>) marcaRepository.findAll();
    }

}
