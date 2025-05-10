package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.entity.ModeloEntity;
import cl.alcoholicos.gestorestacionamiento.repository.ModeloRepository;
import cl.alcoholicos.gestorestacionamiento.service.IModelo;

@Service
public class ModeloService implements IModelo {

    @Autowired
    private ModeloRepository modeloRepository;

    @Override
    public ModeloEntity insert(ModeloEntity modelo) {
        return modeloRepository.save(modelo);
    }

    @Override
    public ModeloEntity update(Integer idModelo, ModeloEntity modelo) {
        modelo.setIdModelo(idModelo);
        return modeloRepository.save(modelo);
    }

    @Override
    public ModeloEntity delete(Integer idModelo) {
        modeloRepository.deleteById(idModelo);
        return null;
    }

    @Override
    public ModeloEntity getById(Integer idModelo) {
        return modeloRepository.findById(idModelo).get();
    }

    @Override
    public List<ModeloEntity> getAll() {
        return (List<ModeloEntity>) modeloRepository.findAll();
    }

}
