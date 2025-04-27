package cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.tipoestadoreserva.entity.TipoEstadoReservaEntity;

@Repository
public interface TipoEstadoReservaRepository  extends JpaRepository<TipoEstadoReservaEntity, Integer> {

}
