package cl.alcoholicos.gestorestacionamiento.marca.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.marca.dto.MarcaUpdateDTO;
public interface IMarca {
    
    MarcaResponseDTO insert(MarcaCreateDTO marca);

    MarcaResponseDTO update(int idMarca, MarcaUpdateDTO marca);

    boolean delete(int idMarca);

    MarcaResponseDTO getById(int idMarca);

    List<MarcaResponseDTO> getAll();

}
