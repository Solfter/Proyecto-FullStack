package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.entity.ModeloEntity;

public interface IModelo {
    
    ModeloEntity insert(ModeloEntity modelo);

    ModeloEntity update(Integer idModelo, ModeloEntity modelo);

    ModeloEntity delete(Integer idModelo);

    ModeloEntity getById(Integer idModelo);

    List<ModeloEntity> getAll();

}
