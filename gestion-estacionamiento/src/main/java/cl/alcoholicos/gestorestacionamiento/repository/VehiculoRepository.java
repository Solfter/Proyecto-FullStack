package cl.alcoholicos.gestorestacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.VehiculoEntity;

@Repository
public interface VehiculoRepository  extends JpaRepository<VehiculoEntity, String> {

}
