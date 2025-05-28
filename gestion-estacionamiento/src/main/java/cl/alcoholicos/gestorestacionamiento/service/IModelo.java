package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.ModeloCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ModeloResponseDTO;

public interface IModelo {
    
    ModeloResponseDTO insert(ModeloCreateDTO modelo);

    ModeloResponseDTO update(Integer idModelo, ModeloResponseDTO modelo);

    boolean delete(Integer idModelo);

    ModeloResponseDTO getById(Integer idModelo);

    List<ModeloResponseDTO> getAll();

}
