package cl.estacionamiento.usuario.service;

import java.util.List;

import cl.estacionamiento.usuario.dto.MarcaDTO;

public interface IMarca {
           MarcaDTO insert(MarcaDTO marca);

    MarcaDTO update(Integer idMarca, MarcaDTO marca);

    MarcaDTO delete(Integer idMarca);

    MarcaDTO getById(Integer idMarca);

    List<MarcaDTO> getAll();

}
