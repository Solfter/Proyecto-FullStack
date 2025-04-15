package cl.alcoholicos.gestorestacionamiento.marca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaDTO;
import cl.alcoholicos.gestorestacionamiento.marca.repository.MarcaRepository;
import cl.alcoholicos.gestorestacionamiento.marca.service.IMarca;

@Service
public class MarcaService implements IMarca {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public MarcaDTO insert(MarcaDTO marca) {
        return marcaRepository.save(marca);
    }

    @Override
    public MarcaDTO update(Integer idMarca, MarcaDTO marca) {
        marca.setIdMarca(idMarca);
        return marcaRepository.save(marca);
    }

    @Override
    public MarcaDTO delete(Integer idMarca) {
        marcaRepository.deleteById(idMarca);
        return null;
    }

    @Override
    public MarcaDTO getById(Integer idMarca) {
        return marcaRepository.findById(idMarca).get();
    }

    @Override
    public List<MarcaDTO> getAll() {
        return (List<MarcaDTO>) marcaRepository.findAll();
    }

}
