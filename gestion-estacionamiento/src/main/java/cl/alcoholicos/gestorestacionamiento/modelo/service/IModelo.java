package cl.alcoholicos.gestorestacionamiento.modelo.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.modelo.dto.ModeloDTO;

public interface IModelo {
    
    ModeloDTO insert(ModeloDTO modelo);

    ModeloDTO update(Integer idModelo, ModeloDTO modelo);

    ModeloDTO delete(Integer idModelo);

    ModeloDTO getById(Integer idModelo);

    List<ModeloDTO> getAll();

}
