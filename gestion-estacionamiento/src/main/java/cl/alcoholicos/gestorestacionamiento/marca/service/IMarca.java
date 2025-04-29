package cl.alcoholicos.gestorestacionamiento.marca.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaResponseDTO;
public interface IMarca {
    
    MarcaResponseDTO insert(MarcaResponseDTO marca);

    MarcaResponseDTO update(Integer idMarca, MarcaResponseDTO marca);

    MarcaResponseDTO delete(Integer idMarca);

    MarcaResponseDTO getById(Integer idMarca);

    List<MarcaResponseDTO> getAll();

}
