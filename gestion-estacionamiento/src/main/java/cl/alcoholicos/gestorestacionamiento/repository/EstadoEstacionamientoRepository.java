package cl.alcoholicos.gestorestacionamiento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.EstadoEstacionamientoEntity;

@Repository
public interface EstadoEstacionamientoRepository extends JpaRepository<EstadoEstacionamientoEntity, Integer> {

    Optional<EstadoEstacionamientoEntity> findByDescEstadoEstacionamiento(String descEstadoEstacionamiento);
    
}
