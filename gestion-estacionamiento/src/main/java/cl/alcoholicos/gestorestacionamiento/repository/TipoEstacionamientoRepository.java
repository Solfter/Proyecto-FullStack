package cl.alcoholicos.gestorestacionamiento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.alcoholicos.gestorestacionamiento.entity.TipoEstacionamientoEntity;

public interface TipoEstacionamientoRepository extends JpaRepository<TipoEstacionamientoEntity, Integer>{

    Optional<TipoEstacionamientoEntity> findByDescTipoEstacionamiento(String descTipoEstacionamiento);
}
