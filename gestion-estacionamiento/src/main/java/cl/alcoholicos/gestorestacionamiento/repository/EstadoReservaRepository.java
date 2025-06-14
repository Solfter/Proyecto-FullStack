package cl.alcoholicos.gestorestacionamiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.EstadoReservaEntity;

@Repository
public interface EstadoReservaRepository  extends JpaRepository<EstadoReservaEntity, Integer> {
   
    List<EstadoReservaEntity> findAllByReservaIdReserva (Integer reservaId);
}
