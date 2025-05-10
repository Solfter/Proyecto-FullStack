package cl.alcoholicos.gestorestacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.EstadoReservaEntity;

@Repository
public interface EstadoReservaRepository  extends JpaRepository<EstadoReservaEntity, Integer> {

}
