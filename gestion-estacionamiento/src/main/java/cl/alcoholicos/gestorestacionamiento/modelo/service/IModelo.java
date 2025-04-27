package cl.alcoholicos.gestorestacionamiento.modelo.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.modelo.entity.ModeloEntity;

public interface IModelo {
    
    ModeloEntity insert(ModeloEntity modelo);

    ModeloEntity update(Integer idModelo, ModeloEntity modelo);

    ModeloEntity delete(Integer idModelo);

    ModeloEntity getById(Integer idModelo);

    List<ModeloEntity> getAll();

}
