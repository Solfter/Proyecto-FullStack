package cl.alcoholicos.gestorestacionamiento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.TipoEstadoReservaEntity;

@Repository
public interface TipoEstadoReservaRepository  extends JpaRepository<TipoEstadoReservaEntity, Integer> {

    Optional<TipoEstadoReservaEntity> findByDescTipoEstadoReserva(String descTipoEstadoReserva);
}
