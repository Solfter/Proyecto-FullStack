package cl.alcoholicos.gestorestacionamiento.estacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.estacionamiento.entity.EstacionamientoEntity;

public interface IEstacionamiento{

    EstacionamientoEntity insert(EstacionamientoEntity estacionamiento);

    EstacionamientoEntity update(Integer idEstacionamiento, EstacionamientoEntity estacionamiento);

    EstacionamientoEntity delete(Integer idEstacionamiento);

    EstacionamientoEntity getById(Integer idEstacionamiento);

    List<EstacionamientoEntity> getAll();

}
