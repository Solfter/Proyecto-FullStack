package cl.alcoholicos.gestorestacionamiento.marca.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.marca.entity.MarcaEntity;

public interface IMarca {
    
    MarcaEntity insert(MarcaEntity marca);

    MarcaEntity update(Integer idMarca, MarcaEntity marca);

    MarcaEntity delete(Integer idMarca);

    MarcaEntity getById(Integer idMarca);

    List<MarcaEntity> getAll();

}
