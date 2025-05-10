package cl.alcoholicos.gestorestacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.EstacionamientoEntity;

@Repository
public interface EstacionamientoRepository extends JpaRepository<EstacionamientoEntity, Integer> {

}
