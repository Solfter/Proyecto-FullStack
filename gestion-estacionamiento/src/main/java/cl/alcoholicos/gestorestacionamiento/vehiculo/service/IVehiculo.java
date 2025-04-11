package cl.alcoholicos.gestorestacionamiento.vehiculo.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoDTO;

public interface IVehiculo {

    VehiculoDTO insert(VehiculoDTO vehiculo);

    VehiculoDTO update(String patente, VehiculoDTO vehiculo);

    VehiculoDTO delete(String patente);

    VehiculoDTO getById(String patente);

    List<VehiculoDTO> getAll();
    
}
