package cl.alcoholicos.gestorestacionamiento.modelo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.modelo.dto.ModeloDTO;
import cl.alcoholicos.gestorestacionamiento.modelo.repository.ModeloRepository;
import cl.alcoholicos.gestorestacionamiento.modelo.service.IModelo;

@Service
public class ModeloService implements IModelo {

    @Autowired
    private ModeloRepository modeloRepository;

    @Override
    public ModeloDTO insert(ModeloDTO modelo) {
        return modeloRepository.save(modelo);
    }

    @Override
    public ModeloDTO update(Integer idModelo, ModeloDTO modelo) {
        modelo.setIdModelo(idModelo);
        return modeloRepository.save(modelo);
    }

    @Override
    public ModeloDTO delete(Integer idModelo) {
        modeloRepository.deleteById(idModelo);
        return null;
    }

    @Override
    public ModeloDTO getById(Integer idModelo) {
        return modeloRepository.findById(idModelo).get();
    }

    @Override
    public List<ModeloDTO> getAll() {
        return (List<ModeloDTO>) modeloRepository.findAll();
    }

}
