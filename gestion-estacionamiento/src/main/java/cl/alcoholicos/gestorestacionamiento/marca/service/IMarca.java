package cl.alcoholicos.gestorestacionamiento.marca.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaDTO;

public interface IMarca {
    
    MarcaDTO insert(MarcaDTO marca);

    MarcaDTO update(Integer idMarca, MarcaDTO marca);

    MarcaDTO delete(Integer idMarca);

    MarcaDTO getById(Integer idMarca);

    List<MarcaDTO> getAll();

}
