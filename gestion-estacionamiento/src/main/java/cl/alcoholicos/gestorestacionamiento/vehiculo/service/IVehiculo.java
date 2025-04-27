package cl.alcoholicos.gestorestacionamiento.vehiculo.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.vehiculo.entity.VehiculoEntity;

public interface IVehiculo {

    VehiculoEntity insert(VehiculoEntity vehiculo);

    VehiculoEntity update(String patente, VehiculoEntity vehiculo);

    VehiculoEntity delete(String patente);

    VehiculoEntity getById(String patente);

    List<VehiculoEntity> getAll();
    
}
