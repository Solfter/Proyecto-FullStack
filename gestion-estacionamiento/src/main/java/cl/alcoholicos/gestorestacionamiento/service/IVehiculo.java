package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.VehiculoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.VehiculoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.VehiculoUpdateDTO;

public interface IVehiculo {

    VehiculoResponseDTO insert(VehiculoCreateDTO vehiculo);

    VehiculoResponseDTO update(String patente, VehiculoUpdateDTO vehiculo);

    boolean delete(String patente);

    VehiculoResponseDTO getById(String patente);

    List<VehiculoResponseDTO> getAll();

    List<VehiculoResponseDTO> getAllByUsuario(Integer rut);
    
}
