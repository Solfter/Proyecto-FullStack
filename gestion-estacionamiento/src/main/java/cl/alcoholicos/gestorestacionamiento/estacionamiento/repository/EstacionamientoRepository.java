package cl.alcoholicos.gestorestacionamiento.estacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.estacionamiento.entity.EstacionamientoEntity;

@Repository
public interface EstacionamientoRepository extends JpaRepository<EstacionamientoEntity, Integer> {

}
