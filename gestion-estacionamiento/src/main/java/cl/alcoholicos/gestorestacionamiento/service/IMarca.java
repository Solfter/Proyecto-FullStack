package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.MarcaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.MarcaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.MarcaUpdateDTO;
public interface IMarca {
    
    MarcaResponseDTO insert(MarcaCreateDTO marca);

    MarcaResponseDTO update(int idMarca, MarcaUpdateDTO marca);

    boolean delete(int idMarca);

    MarcaResponseDTO getById(int idMarca);

    List<MarcaResponseDTO> getAll();

}
