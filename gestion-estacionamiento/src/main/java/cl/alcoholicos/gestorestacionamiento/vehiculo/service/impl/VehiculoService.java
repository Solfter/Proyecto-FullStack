package cl.alcoholicos.gestorestacionamiento.vehiculo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.vehiculo.dto.VehiculoDTO;
import cl.alcoholicos.gestorestacionamiento.vehiculo.repository.VehiculoRepository;
import cl.alcoholicos.gestorestacionamiento.vehiculo.service.IVehiculo;

@Service
public class VehiculoService implements IVehiculo {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public VehiculoDTO insert(VehiculoDTO vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public VehiculoDTO update(String patente, VehiculoDTO vehiculo) {
        vehiculo.setPatente(patente);
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public VehiculoDTO delete(String patente) {
        vehiculoRepository.deleteById(patente);
        return null;
    }

    @Override
    public VehiculoDTO getById(String patente) {
        return vehiculoRepository.findById(patente).get();
    }

    @Override
    public List<VehiculoDTO> getAll() {
        return (List<VehiculoDTO>) vehiculoRepository.findAll();
    }

}
