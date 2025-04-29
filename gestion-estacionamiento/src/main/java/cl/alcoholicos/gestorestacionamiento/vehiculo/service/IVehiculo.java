package cl.alcoholicos.gestorestacionamiento.vehiculo.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoCreateDTO;
import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoResponseDTO;
import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoUpdateDTO;

public interface IVehiculo {

    VehiculoResponseDTO insert(VehiculoCreateDTO vehiculo);

    VehiculoResponseDTO update(String patente, VehiculoUpdateDTO vehiculo);

    boolean delete(String patente);

    VehiculoResponseDTO getById(String patente);

    List<VehiculoResponseDTO> getAll();
    
}
