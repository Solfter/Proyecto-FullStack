package cl.alcoholicos.gestorestacionamiento.vehiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.vehiculo.entity.VehiculoEntity;

@Repository
public interface VehiculoRepository  extends JpaRepository<VehiculoEntity, String> {

}
