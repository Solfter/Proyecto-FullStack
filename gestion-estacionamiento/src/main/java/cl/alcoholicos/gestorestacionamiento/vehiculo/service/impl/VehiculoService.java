package cl.alcoholicos.gestorestacionamiento.vehiculo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.vehiculo.entity.VehiculoEntity;
import cl.alcoholicos.gestorestacionamiento.vehiculo.repository.VehiculoRepository;
import cl.alcoholicos.gestorestacionamiento.vehiculo.service.IVehiculo;

@Service
public class VehiculoService implements IVehiculo {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public VehiculoEntity insert(VehiculoEntity vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public VehiculoEntity update(String patente, VehiculoEntity vehiculo) {
        vehiculo.setPatente(patente);
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public VehiculoEntity delete(String patente) {
        vehiculoRepository.deleteById(patente);
        return null;
    }

    @Override
    public VehiculoEntity getById(String patente) {
        return vehiculoRepository.findById(patente).get();
    }

    @Override
    public List<VehiculoEntity> getAll() {
        return (List<VehiculoEntity>) vehiculoRepository.findAll();
    }

}
